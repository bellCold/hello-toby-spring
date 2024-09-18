package tobyspring.hellospring.api

import java.net.HttpURLConnection
import java.net.URI

class SimpleApiExecutor : ApiExecutor {
    override fun execute(uri: URI): String {
        val connection = uri.toURL().openConnection() as HttpURLConnection
        return connection.inputStream.bufferedReader().use { it.readText() }
    }
}