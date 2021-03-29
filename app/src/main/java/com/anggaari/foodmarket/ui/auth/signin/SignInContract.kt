package com.anggaari.foodmarket.ui.auth.signin

import com.anggaari.foodmarket.model.base.BasePresenter
import com.anggaari.foodmarket.model.base.BaseView
import com.anggaari.foodmarket.model.response.login.LoginResponse

interface SignInContract {
    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter: SignInContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}