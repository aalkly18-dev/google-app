package com.uranium.agent.net

import java.net.HttpURLConnection
import java.net.URL

object ApiClient {
    fun postRequest(urlString: String, payload: ByteArray): ByteArray? {
        return try {
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.outputStream.write(payload)
            conn.inputStream.readBytes()
        } catch (e: Exception) {
            null
        }
    }
}
