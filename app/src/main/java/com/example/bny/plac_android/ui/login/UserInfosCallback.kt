package com.example.bny.plac_android.ui.login
import com.example.bny.plac_android.ui.data.Result
import org.json.JSONObject


interface UserInfosCallback {
    fun onSuccessUserInfos(res : Result<JSONObject>)
}