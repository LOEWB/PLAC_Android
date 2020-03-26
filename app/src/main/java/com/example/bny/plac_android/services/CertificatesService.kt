package com.example.bny.plac_android.services

import android.content.Context
import com.example.bny.plac_android.repositories.AuthenticationRepository
import com.example.bny.plac_android.repositories.CertificatesRepository
import com.example.bny.plac_android.ui.data.Result
import com.example.bny.plac_android.ui.login.CertificatesCallback
import com.example.bny.plac_android.ui.login.TokenCallback
import com.example.bny.plac_android.ui.login.UserInfosCallback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CertificatesService @Inject constructor(val rep: CertificatesRepository) {

    fun getCertif(token: String, context: Context, callback: CertificatesCallback) {
        rep.retrieveCertif(token, context, callback)
    }
}