package com.anggaari.foodmarket.ui.order.detailsorders

import com.anggaari.foodmarket.base.BasePresenter
import com.anggaari.foodmarket.base.BaseView

interface OrdersDetailContract {
    interface View : BaseView {
        fun onUpdateTransactionSuccess(message: String)
        fun onUpdateTransactionFailed(message: String)
    }

    interface Presenter : OrdersDetailContract, BasePresenter {
        fun getUpdateTransaction(id:String, status:String)
    }
}