package com.example.bny.plac_android.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import android.util.Patterns
import com.example.bny.plac_android.R
import com.example.bny.plac_android.services.AuthenticationService
import com.example.bny.plac_android.ui.data.Result
import org.json.JSONObject


class LoginViewModel : ViewModel(), TokenCallback, UserInfosCallback {


    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    lateinit var username: String
    lateinit var password: String
    lateinit var authServ: AuthenticationService
    lateinit var context: Context



    fun login(username: String, password: String, context: Context, authServ: AuthenticationService) {

        // can be launched in a separate asynchronous job
        this.username = username
        this.password= password
        this.authServ = authServ
        this.context = context
        authServ.getToken(username, password, context, this)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    // Called at token retrieve
    override fun onSuccessToken(res: Result<String>) {
        if (res is Result.Success) {
            val token: String = res.data() as String
            authServ.getUserInfos(token, context, this)
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }

    }

    override fun onSuccessUserInfos(res: Result<JSONObject>) {
        if (res is Result.Success) {
            val json: JSONObject = res.data() as JSONObject
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = json.getString("firstname")))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }
}
