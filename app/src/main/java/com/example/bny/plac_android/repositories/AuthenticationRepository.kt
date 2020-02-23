package com.example.bny.plac_android.repositories

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton

class AuthenticationRepository @Inject constructor(context: Context) {

    val TAG = "OK_BOOMER"

    val queue = Volley.newRequestQueue(context)
    val url = "http://192.168.42.177:8088/authenticate"

    fun retrieveToken(): String {
        var res = String()

        val params = HashMap<String, String>()
        params["Content-Type"] = "application/json"
        params["login"] = "Pierre"
        params["password"] = "1234"
        val jsonObject = JSONObject(params)

        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener { response ->
                    // Display the first 500 characters of the response string.
                    res = "Response is: $response"
                    Log.d(TAG, "ok : $res")
                },
                Response.ErrorListener {
                    error ->

                    res = "That didn't work!"
                    Log.d(TAG, "err : $error")
                })

        queue.add(jsonRequest)

        return res
    }
}