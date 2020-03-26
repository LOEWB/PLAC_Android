package com.example.bny.plac_android.repositories

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bny.plac_android.ui.data.Result
import com.example.bny.plac_android.ui.login.CertificatesCallback
import com.example.bny.plac_android.ui.login.TokenCallback
import com.example.bny.plac_android.ui.login.UserInfosCallback
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CertificatesRepository @Inject constructor() {

    val TAG = "OK_BOOMER"


    val url_certif = "http://192.168.42.177:8088/api/certificates/mine"

    fun retrieveCertif(token: String, context: Context, callback: CertificatesCallback) {
        Log.d("OK_BOOMER_CERTIF", "rep certif")
        val queue = Volley.newRequestQueue(context)

        Log.d(TAG, "Bearer $token")

        val jsonArray = JSONArray()

        val getRequest: JsonArrayRequest = object : JsonArrayRequest(Request.Method.GET, url_certif, jsonArray,
                Response.Listener { response ->
                    // response
//                    Log.d("Response", response.getString("firstname"))
                    callback.onSuccessCertificates(Result.Success(response))
                    Log.d("OK_BOOMER_CERTIF", "ok => $response")

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