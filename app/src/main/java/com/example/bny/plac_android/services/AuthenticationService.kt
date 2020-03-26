package com.example.bny.plac_android.services

import android.content.Context
import android.util.Log
import com.example.bny.plac_android.repositories.AuthenticationRepository
import com.example.bny.plac_android.ui.data.Result
import com.example.bny.plac_android.ui.login.TokenCallback
import com.example.bny.plac_android.ui.login.UserInfosCallback
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(val rep: AuthenticationRepository) : TokenCallback, UserInfosCallback/* @Inject constructor(private val repo: AuthenticationRepository)*/ {

//    var token: String = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaWNvbGFzLnJvbGxhbmQiLCJleHAiOjE1ODUxODQ5OTYsImlhdCI6MTU4NTE0ODk5Nn0.HgqBbEVljafEUD97ub02UM5f8hT010Lny_jaJVNcne4"
    lateinit var token: String
    lateinit var tokenCallback: TokenCallback
    lateinit var userCallback: UserInfosCallback
    lateinit var jsonUser: JSONObject

    fun getToken(username: String, password: String, context: Context, callback: TokenCallback) {
        if(::token.isInitialized) {
            callback.onSuccessToken(Result.Success(token))
            Log.d("OK_BOOMER", "token in mem")
        }
        else {
            this.tokenCallback = callback
            rep.retrieveToken(username, password, context, this)
            Log.d("OK_BOOMER", "token in rep")
        }
    }
    fun getToken(callback: TokenCallback) {
        if(::token.isInitialized) {
            callback.onSuccessToken(Result.Success(token))
            Log.d("OK_BOOMER", "token in mem")
        }
    }

    fun getUserInfos(token: String, context: Context, callback: UserInfosCallback) {
        if(::jsonUser.isInitialized)
            callback.onSuccessUserInfos(Result.Success(jsonUser))
        else {
            this.userCallback = callback
            rep.retrieveUserByToken(token, context, this)
        }
    }

    override fun onSuccessToken(res : Result<String>) {
        if(res is Result.Success)
        {
            val token: String = res.data() as String
            this.token = token
        }
        tokenCallback.onSuccessToken(res)
    }

    override fun onSuccessUserInfos(res: Result<JSONObject>) {
        if(res is Result.Success)
        {
            val json: JSONObject = res.data() as JSONObject
            this.jsonUser = json
        }
        userCallback.onSuccessUserInfos(res)
    }


}