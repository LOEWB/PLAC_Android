package com.example.bny.plac_android.ui.login
import com.example.bny.plac_android.ui.data.Result


interface TokenCallback {
    fun onSuccessToken(res : Result<String>)
}