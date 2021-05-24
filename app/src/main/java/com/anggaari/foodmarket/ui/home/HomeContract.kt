package com.anggaari.foodmarket.ui.home

import com.anggaari.foodmarket.base.BasePresenter
import com.anggaari.foodmarket.base.BaseView
import com.anggaari.foodmarket.model.response.home.HomeResponse

interface HomeContract {
    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter: HomeContract, BasePresenter {
        fun getHome()
    }
}