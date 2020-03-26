package com.example.bny.plac_android.di

import com.example.bny.plac_android.ui.MainActivity
import com.example.bny.plac_android.services.AuthenticationService
import com.example.bny.plac_android.ui.CertificatesActivity
import com.example.bny.plac_android.ui.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[MainActivityModule::class])
interface ApplicationComponent {
    fun getAuthService(): AuthenticationService
    fun inject(mainActivity: MainActivity)
    fun injectLogin(loginActivity: LoginActivity)
    fun injectCertificates(certificatesActivity: CertificatesActivity)
}