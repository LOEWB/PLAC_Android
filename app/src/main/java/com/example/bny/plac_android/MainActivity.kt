package com.example.bny.plac_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bny.plac_android.di.ApplicationComponent
import com.example.bny.plac_android.di.DaggerApplicationComponent
import com.example.bny.plac_android.di.MainActivityModule
import com.example.bny.plac_android.services.AuthenticationService
import com.example.bny.plac_android.ui.login.LoginActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var authServ: AuthenticationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component: ApplicationComponent = DaggerApplicationComponent.builder()
                .mainActivityModule(MainActivityModule(this)).build()

        component.inject(this)
        Log.d("PLAC_APP", "msg: " + authServ.getToken())

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }
}
