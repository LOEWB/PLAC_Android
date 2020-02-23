package com.example.bny.plac_android.services

import android.content.Context
import com.example.bny.plac_android.repositories.AuthenticationRepository
import javax.inject.Inject


class AuthenticationService(context: Context)/* @Inject constructor(private val repo: AuthenticationRepository)*/ {

    val rep: AuthenticationRepository = AuthenticationRepository(context)

    fun getToken(): String {
        return rep.retrieveToken()
        //return repo.retrieveToken()
    }
}