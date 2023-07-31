package com.example.android_coroutine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_coroutine.domain.LoginResponse
import com.example.android_coroutine.domain.User
import com.example.android_coroutine.repository.LoginRepository
import com.example.android_coroutine.repository.Result
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(){
    private val _user: MutableLiveData<User?> = MutableLiveData()
    val user : LiveData<User?>
        get() = _user

    fun login(username: String, token: String) {
        viewModelScope.launch {
            val jsonBody = "{\"username\":\"$username\", \"password\":\"$token\"}"
            when (val result = loginRepository.makeLoginRequest(jsonBody)) {
                is Result.Success<LoginResponse> -> {
                    val data = result.data.data
                    _user.value = extractData(data)
                }
                else -> {
                    _user.value = null
                }
            }
        }
    }

    private fun extractData(data: String): User? {
        val gsonBuider = GsonBuilder()
        val gson = gsonBuider.create()
        return try  {
            gson.fromJson(data, User::class.java)
        }catch (e: JsonSyntaxException) {
            null
        }
    }
}