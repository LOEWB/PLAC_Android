package com.example.bny.plac_android.ui.login
import com.example.bny.plac_android.ui.data.Result
import org.json.JSONArray
import org.json.JSONObject


interface CertificatesCallback {
    fun onSuccessCertificates(res : Result<JSONArray>)
}