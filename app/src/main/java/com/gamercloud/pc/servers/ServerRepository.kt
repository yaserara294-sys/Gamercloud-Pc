
package com.gamercloud.pc.servers

object ServerRepository {

    fun addServer(
        server: GameServer,
        config: ServerConfig
    ): Boolean {
        if (!config.isValid()) return false

        if (server.id != config.serverId) return false

        if (ServerManager.getServer(server.id) != null) {
            return false
        }

        val added = ServerManager.addServer(server)

        if (!added) return false

        val configured = ServerConfigManager.add(config)

        if (!configured) {
            ServerManager.removeServer(server.id)
            return false
        }

        return true
    }

    fun getAllServers(): List<GameServer> {
        return ServerManager.getServers()
    }

    fun getAvailableServers(): List<GameServer> {
        return ServerManager.getEnabledServers()
    }

    fun getServerConfig(id: String): ServerConfig? {
        return ServerConfigManager.getAll()
            .find { it.serverId == id }
    }

    fun selectServer(id: String): Boolean {
        return ServerConfigManager.select(id)
    }

    fun getSelectedServer(): ServerConfig? {
        return ServerConfigManager.getSelected()
    }

    fun removeServer(id: String) {
        ServerManager.removeServer(id)
        ServerConfigManager.remove(id)
    }

    fun getServerCount(): Int {
        return ServerManager.getServers().size
    }
}
