package com.anggaari.foodmarket.ui.order

import com.anggaari.foodmarket.base.BasePresenter
import com.anggaari.foodmarket.base.BaseView
import com.anggaari.foodmarket.model.response.transaction.TransactionResponse

interface OrderContract {
    interface View : BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }
}