package com.uranium.agent.tasks

import android.os.AsyncTask
import com.uranium.agent.net.ApiClient

class AgentTask(private val callback: (String) -> Unit) : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String?): String {
        val url = params[0] ?: return "Invalid URL"
        return ApiClient.sendGetRequest(url)
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        callback(result)
    }
}
