package com.example.android_coroutine.repository

import com.example.android_coroutine.domain.LoginResponse
import java.io.InputStream

class LoginResponseParser {
    // xử lý phản hồi từ sever(api service) khi thực hiệm chức năng đăng nhập
    fun parse(input: InputStream): LoginResponse {
        val str = input.bufferedReader().use { it.readText() }
        return LoginResponse(str)
    }
}