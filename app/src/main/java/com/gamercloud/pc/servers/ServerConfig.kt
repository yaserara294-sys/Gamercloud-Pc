
package com.gamercloud.pc.servers

data class ServerConfig(
    val serverId: String,
    val serverName: String,
    val ipAddress: String,
    val port: Int,
    val region: String,
    val secureConnection: Boolean = true,
    val enabled: Boolean = true
) {
    fun isValid(): Boolean {
        return serverId.isNotBlank() &&
                serverName.isNotBlank() &&
                ipAddress.isNotBlank() &&
                !ipAddress.contains(" ") &&
                port in 1..65535 &&
                region.isNotBlank()
    }
}

object ServerConfigManager {

    private val configurations =
        mutableMapOf<String, ServerConfig>()

    private var selectedServerId: String? = null

    fun add(config: ServerConfig): Boolean {
        if (!config.isValid()) return false

        configurations[config.serverId] = config
        return true
    }

    fun select(serverId: String): Boolean {
        val config = configurations[serverId]
            ?: return false

        if (!config.enabled) return false

        selectedServerId = serverId
        return true
    }

    fun getSelected(): ServerConfig? {
        return selectedServerId?.let {
            configurations[it]
        }
    }

    fun getAll(): List<ServerConfig> {
        return configurations.values.toList()
    }

    fun remove(serverId: String) {
        configurations.remove(serverId)

        if (selectedServerId == serverId) {
            selectedServerId = null
        }
    }

    fun clear() {
        configurations.clear()
        selectedServerId = null
    }
}
