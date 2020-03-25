package com.example.bny.plac_android.repositories

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bny.plac_android.ui.data.Result
import com.example.bny.plac_android.ui.login.TokenCallback
import com.example.bny.plac_android.ui.login.UserInfosCallback
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationRepository @Inject constructor() {

    val TAG = "OK_BOOMER"


    val url_authenticate = "http://192.168.42.177:8088/api/authenticate"
    val url_user_info = "http://192.168.42.177:8088/api/userByToken"

    fun retrieveToken(username: String, password: String, context: Context, callback: TokenCallback) {
        val queue = Volley.newRequestQueue(context)
        lateinit var result: Result<String>

        val params = HashMap<String, String>()
        params["Content-Type"] = "application/json"
        params["login"] = username
        params["password"] = password
        val jsonObject = JSONObject(params)

        val jsonRequest = JsonObjectRequest(Request.Method.POST, url_authenticate, jsonObject,
                Response.Listener { response ->
                    // Display the first 500 characters of the response string.
                    Log.d(TAG, "ok : $response")
                    result = Result.Success(response.getString("jwt"))
                    callback.onSuccessToken(result)
                },
                Response.ErrorListener {
                    error ->

                    Log.d(TAG, "err : $error")
                    result = Result.Error(error)
                    callback.onSuccessToken(result)
                })

        queue.add(jsonRequest)
    }

    fun retrieveUserByToken(token: String, context: Context, callback: UserInfosCallback) {
        val queue = Volley.newRequestQueue(context)

        Log.d(TAG, "Bearer $token")

        val jsonObject = JSONObject()

        val getRequest: JsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url_user_info, jsonObject,
                Response.Listener { response ->
                    // response
//                    Log.d("Response", response.getString("firstname"))
                    callback.onSuccessUserInfos(Result.Success(response))
                },
                Response.ErrorListener { error ->
                    // TODO Auto-generated method stub
                    Log.d("ERROR", "error => $error")
                }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Content-Type"] = "application/json"
                params["Authorization"] = "Bearer $token"
                return params
            }
        }
        queue.add(getRequest)
    }
}