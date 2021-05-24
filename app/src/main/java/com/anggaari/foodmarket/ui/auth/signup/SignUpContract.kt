package com.anggaari.foodmarket.ui.auth.signup

import android.net.Uri
import com.anggaari.foodmarket.base.BasePresenter
import com.anggaari.foodmarket.base.BaseView
import com.anggaari.foodmarket.model.request.RegisterRequest
import com.anggaari.foodmarket.model.response.login.LoginResponse

interface SignUpContract {
    interface View: BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view: android.view.View)
        fun onPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter: SignUpContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(file: Uri, view: android.view.View)
    }
}