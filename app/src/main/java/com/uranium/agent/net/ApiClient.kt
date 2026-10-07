package com.uranium.agent.net

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object ApiClient {
    fun sendGetRequest(requestUrl: String): String {
        val result = StringBuilder()
        try {
            val url = URL(requestUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000
            connection.readTimeout = 10000

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                result.append(line)
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error: ${e.message}"
        }
        return result.toString()
    }
}
