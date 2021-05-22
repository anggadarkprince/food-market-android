package com.anggaari.foodmarket.ui.detail

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.anggaari.foodmarket.FoodMarket
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.home.Data
import com.anggaari.foodmarket.model.response.login.User
import com.anggaari.foodmarket.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*

/**
 * A simple [Fragment] subclass.
 * Use the [PaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFragment : Fragment() {
    var progressDialog: Dialog? = null
    var total : Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarPayment()

        var data = arguments?.getParcelable<Data>("data")
        initView(data)

        btnCheckout.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_payment_success)
        }
    }

    private fun initView(data: Data?) {
        tvTitle.text = data?.foodName
        tvPrice.formatPrice(data?.price.toString())
        Glide.with(requireContext())
            .load(data?.imageUrl)
            .into(ivPoster)

        tvNameItem.text = data?.foodName
        tvPrice.formatPrice(data?.price.toString())

        if (data?.price.toString().isNotEmpty()) {
            val totalTax = data?.price?.toFloat()!!.div(10)
            tvTax.formatPrice(totalTax.toString())

            total = data.price.toFloat() + totalTax + 50000
            tvTotalPrice.formatPrice(total.toString())
        } else {
            tvPrice.text = "IDR. 0"
            tvTax.text = "IDR. 0"
            tvTotalPrice.text = "IDR. 0"
            total = 0f
        }

        val user = FoodMarket.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)

        tvName.text = userResponse?.name
        tvPhone.text = userResponse?.phoneNumber
        tvAddress.text = userResponse?.address
        tvCity.text = userResponse?.city
    }
}