
package com.gamercloud.pc.servers

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object ServerStorage {

    private const val PREFS_NAME = "gamer_cloud_servers"
    private const val SERVERS_KEY = "saved_servers"

    private lateinit var preferences:
        android.content.SharedPreferences

    fun initialize(context: Context) {
        preferences = context.applicationContext
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
    }

    fun saveServer(
        server: GameServer,
        config: ServerConfig
    ): Boolean {
        if (!::preferences.isInitialized) return false
        if (!ServerSecurity.validateServer(server)) return false
        if (!ServerSecurity.validateConfig(config)) return false
        if (server.id != config.serverId) return false

        return try {
            val servers = readServers()

            val item = JSONObject().apply {
                put("id", server.id)
                put("name", server.name)
                put("address", server.address)
                put("port", server.port)
                put("region", server.region)
                put("enabled", server.enabled)
                put("secure", config.secureConnection)
            }

            val updated = JSONArray()
            var found = false

            for (i in 0 until servers.length()) {
                val existing = servers.getJSONObject(i)

                if (existing.optString("id") == server.id) {
                    updated.put(item)
                    found = true
                } else {
                    updated.put(existing)
                }
            }

            if (!found) updated.put(item)

            preferences.edit()
                .putString(SERVERS_KEY, updated.toString())
                .commit()

        } catch (_: Exception) {
            false
        }
    }

    fun loadServers(): Boolean {
        if (!::preferences.isInitialized) return false

        return try {
            ServerManager.clearServers()
            ServerConfigManager.clear()

            val servers = readServers()

            for (i in 0 until servers.length()) {
                val item = servers.getJSONObject(i)

                val server = GameServer(
                    id = item.getString("id"),
                    name = item.getString("name"),
                    address = item.getString("address"),
                    port = item.getInt("port"),
                    region = item.getString("region"),
                    enabled = item.optBoolean("enabled", true)
                )

                val config = ServerConfig(
                    serverId = server.id,
                    serverName = server.name,
                    ipAddress = server.address,
                    port = server.port,
                    region = server.region,
                    secureConnection = item.optBoolean("secure", true),
                    enabled = server.enabled
                )

                if (ServerSecurity.validateServer(server) &&
                    ServerSecurity.validateConfig(config)) {
                    ServerRepository.addServer(server, config)
                }
            }

            true
        } catch (_: Exception) {
            false
        }
    }

    fun deleteServer(id: String): Boolean {
        if (!::preferences.isInitialized) return false

        return try {
            val old = readServers()
            val updated = JSONArray()

            for (i in 0 until old.length()) {
                val item = old.getJSONObject(i)

                if (item.optString("id") != id) {
                    updated.put(item)
                }
            }

            val saved = preferences.edit()
                .putString(SERVERS_KEY, updated.toString())
                .commit()

            if (saved) ServerRepository.removeServer(id)

            saved
        } catch (_: Exception) {
            false
        }
    }

    fun clearStorage(): Boolean {
        if (!::preferences.isInitialized) return false

        return preferences.edit()
            .remove(SERVERS_KEY)
            .commit()
    }

    private fun readServers(): JSONArray {
        val data = preferences.getString(SERVERS_KEY, "[]")
        return JSONArray(data)
    }
}
