package com.anggaari.foodmarket.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.transaction.Data
import com.anggaari.foodmarket.model.response.transaction.TransactionResponse
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class OrderFragment : Fragment(), OrderContract.View {
    lateinit var presenter: OrderPresenter
    var progressDialog: Dialog? = null

    private var inProgressList: ArrayList<Data>? = ArrayList()
    private var pastOrdersList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        include_toolbar.toolbar.title = "Your Orders"
        include_toolbar.toolbar.subtitle = "Wait for the best meal"
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()) {
            include_toolbar.visibility = View.GONE
            ll_tab.visibility = View.GONE
            ll_empty.visibility = View.VISIBLE

        } else {

            for (a in transactionResponse.data.indices) {
                // past order
                if (transactionResponse.data[a].status.equals("ON_DELIVERY", true)
                    || transactionResponse.data[a].status.equals("PENDING", true)) {
                    inProgressList?.add(transactionResponse.data[a])
                    // progress
                } else if (transactionResponse.data[a].status.equals("DELIVERY", true)
                    || transactionResponse.data[a].status.equals("DELIVERED", true)
                    || transactionResponse.data[a].status.equals("CANCELLED", true)
                    || transactionResponse.data[a].status.equals("SUCCESS", true)) {
                    pastOrdersList?.add(transactionResponse.data[a])
                }
            }

            if (inProgressList.isNullOrEmpty() && pastOrdersList.isNullOrEmpty()) {
                include_toolbar.visibility = View.GONE
                ll_tab.visibility = View.GONE
                ll_empty.visibility = View.VISIBLE
            } else {
                val sectionsPagerAdapter = SectionsPagerAdapter(
                    childFragmentManager
                )
                sectionsPagerAdapter.setData(inProgressList, pastOrdersList)
                //viewPager!!.offscreenPageLimit = 3
                viewPager.adapter = sectionsPagerAdapter
                tabLayout.setupWithViewPager(viewPager)
            }
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}