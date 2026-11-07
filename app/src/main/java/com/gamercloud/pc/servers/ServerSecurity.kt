
package com.gamercloud.pc.servers

object ServerSecurity {

    private val allowedPorts = 1..65535

    fun validateServer(server: GameServer): Boolean {
        return server.id.isNotBlank() &&
                server.name.isNotBlank() &&
                validateAddress(server.address) &&
                server.port in allowedPorts
    }

    fun validateConfig(config: ServerConfig): Boolean {
        return config.isValid() &&
                validateAddress(config.ipAddress) &&
                config.port in allowedPorts
    }

    fun validateAddress(address: String): Boolean {
        val value = address.trim()

        if (value.isEmpty() || value.length > 253) {
            return false
        }

        if (value.contains("://") ||
            value.contains("/") ||
            value.contains("@") ||
            value.contains(" ")) {
            return false
        }

        return isValidIpv4(value) ||
                isValidHostname(value)
    }

    private fun isValidIpv4(value: String): Boolean {
        val parts = value.split(".")

        if (parts.size != 4) return false

        return parts.all {
            val number = it.toIntOrNull()
            number != null &&
                    number in 0..255 &&
                    it == number.toString()
        }
    }

    private fun isValidHostname(value: String): Boolean {
        if (value.length > 253) return false

        return value.split(".").all { part ->
            part.isNotEmpty() &&
                    part.length <= 63 &&
                    part.first().isLetterOrDigit() &&
                    part.last().isLetterOrDigit() &&
                    part.all {
                        it.isLetterOrDigit() || it == '-'
                    }
        }
    }

    fun requiresSecureConnection(
        config: ServerConfig
    ): Boolean {
        return config.secureConnection
    }
}
