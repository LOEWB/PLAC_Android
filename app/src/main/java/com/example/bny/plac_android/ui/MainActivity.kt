package com.example.bny.plac_android.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.bny.plac_android.R
import com.example.bny.plac_android.services.AuthenticationService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var authServ: AuthenticationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val component: ApplicationComponent = DaggerApplicationComponent.builder()
//                .mainActivityModule(MainActivityModule(this)).build()

//        DaggerApplicationComponent.builder().build().inject(this)
//        Log.d("PLAC_APP", "msg: " + authServ.getToken(this))

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }
}