package com.anggaari.foodmarket.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.anggaari.foodmarket.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    fun toolbarPayment() {
        toolbar.title = "Payment"
        toolbar.subtitle = "You deserve better meal"
        toolbar.visibility = View.VISIBLE;
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarDetail() {
        toolbar.visibility = View.GONE;
    }
}