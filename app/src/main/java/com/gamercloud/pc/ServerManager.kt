
package com.gamercloud.pc.servers

data class GameServer(
    val id: String,
    val name: String,
    val address: String,
    val port: Int,
    val region: String,
    val enabled: Boolean = true
)

object ServerManager {

    private val servers = mutableListOf<GameServer>()

    fun addServer(server: GameServer): Boolean {
        if (server.id.isBlank() ||
            server.name.isBlank() ||
            server.address.isBlank() ||
            server.port !in 1..65535
        ) return false

        if (servers.any { it.id == server.id }) {
            return false
        }

        servers.add(server)
        return true
    }

    fun removeServer(id: String) {
        servers.removeAll { it.id == id }
    }

    fun getServers(): List<GameServer> {
        return servers.toList()
    }

    fun getEnabledServers(): List<GameServer> {
        return servers.filter { it.enabled }
    }

    fun getServer(id: String): GameServer? {
        return servers.find { it.id == id }
    }

    fun clearServers() {
        servers.clear()
    }

    fun isValidAddress(address: String): Boolean {
        return address.isNotBlank() &&
                !address.contains(" ")
    }
}
