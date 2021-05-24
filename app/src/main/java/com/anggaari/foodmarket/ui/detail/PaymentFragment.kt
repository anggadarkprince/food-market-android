package com.anggaari.foodmarket.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.anggaari.foodmarket.FoodMarket
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.checkout.CheckoutResponse
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
class PaymentFragment : Fragment(), PaymentContract.View {
    var progressDialog: Dialog? = null
    lateinit var presenter: PaymentPresenter
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

        val data = arguments?.getParcelable<Data>("data")
        initView(data)
        initView()

        presenter = PaymentPresenter(this)
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
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

        btnCheckout.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(),
                it
            )
        }
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(intent)
        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}