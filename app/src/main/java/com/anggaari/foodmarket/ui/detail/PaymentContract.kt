package com.anggaari.foodmarket.ui.detail

import com.anggaari.foodmarket.base.BasePresenter
import com.anggaari.foodmarket.base.BaseView
import com.anggaari.foodmarket.model.response.checkout.CheckoutResponse

interface PaymentContract {
    interface View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter: PaymentContract, BasePresenter {
        fun getCheckout(foodId: String, userId: String, quantity: String, total: String, view: android.view.View)
    }
}