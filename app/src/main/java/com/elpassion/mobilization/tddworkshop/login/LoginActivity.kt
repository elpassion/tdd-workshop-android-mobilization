package com.elpassion.mobilization.tddworkshop.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.android.view.hide
import com.elpassion.android.view.show
import com.elpassion.mobilization.tddworkshop.MainActivity
import com.elpassion.mobilization.tddworkshop.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity(), Login.View {

    private val loginController by lazy {
        LoginController(api, this, object : Login.Repository {
            override fun save(user: Login.User) = Unit
        }, Schedulers.io(), AndroidSchedulers.mainThread())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        loginButton.setOnClickListener {
            loginController.onLogin(emailInput.text.toString(), passwordInput.text.toString())
        }
    }

    override fun showLoader() {
        loader.show()
    }

    override fun hideLoader() {
        loader.hide()
    }

    override fun openNextScreen() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showLoginCallError() {
        errorMessage.setText(R.string.login_call_error)
    }

    override fun showEmptyEmailError() {
        emptyEmailError.show()
    }

    override fun showEmptyPasswordError() {
        emptyPasswordError.show()
    }

    companion object {
        lateinit var api: Login.Api
    }
}