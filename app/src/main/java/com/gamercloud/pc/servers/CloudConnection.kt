
package com.gamercloud.pc.servers

import java.net.InetSocketAddress
import java.net.Socket

enum class ConnectionStatus {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    ERROR
}

object CloudConnection {

    private var socket: Socket? = null

    @Volatile
    var status: ConnectionStatus =
        ConnectionStatus.DISCONNECTED
        private set

    @Volatile
    var errorMessage: String? = null
        private set

    fun connect(
        address: String,
        port: Int,
        timeout: Int = 5000
    ): Boolean {

        disconnect()

        if (address.isBlank() || port !in 1..65535) {
            status = ConnectionStatus.ERROR
            errorMessage = "Invalid server address or port"
            return false
        }

        status = ConnectionStatus.CONNECTING

        return try {
            val newSocket = Socket()

            newSocket.connect(
                InetSocketAddress(address, port),
                timeout
            )

            socket = newSocket
            status = ConnectionStatus.CONNECTED
            errorMessage = null

            true

        } catch (e: Exception) {
            status = ConnectionStatus.ERROR
            errorMessage = e.message ?: "Connection failed"
            false
        }
    }

    fun disconnect() {
        try {
            socket?.close()
        } catch (_: Exception) {
        }

        socket = null
        status = ConnectionStatus.DISCONNECTED
    }

    fun isConnected(): Boolean {
        return socket?.isConnected == true &&
                socket?.isClosed == false
    }

    fun getStatus(): ConnectionStatus {
        return status
    }

    fun getError(): String? {
        return errorMessage
    }
}
