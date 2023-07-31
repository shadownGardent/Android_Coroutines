package com.example.android_coroutine.repository

import com.example.android_coroutine.domain.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL

class LoginRepository(private val responseParser: LoginResponseParser) {
    private val loginUrl = "https://thantrieu.com/services/services.php/user"

    suspend fun makeLoginRequest(jsonBody: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            val url = URL(loginUrl)
            var result: Result<LoginResponse>? = null
            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; utf-8")
                setRequestProperty("Accept", "application/json")
                doOutput = true
                outputStream.write(jsonBody.toByteArray())
                try {
                    result = Result.Success(responseParser.parse(inputStream))
                } catch (_: FileNotFoundException) {
                }

            }
            result ?: Result.Error(Exception("Không thể mở kết nối đến HttpUrlConnection"))
        }
    }
}