package com.anggaari.foodmarket.ui.order.pastorder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.transaction.Data
import com.anggaari.foodmarket.ui.order.detailsorders.OrdersDetailActivity
import com.anggaari.foodmarket.ui.order.inprogress.PastOrdersAdapter
import kotlinx.android.synthetic.main.fragment_pastorders.*

/**
 * A simple [Fragment] subclass.
 * Use the [PastOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PastOrderFragment : Fragment(), PastOrdersAdapter.ItemAdapterCallback {

    private var adapter: PastOrdersAdapter? = null
    private var pastodersList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pastorders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastodersList = arguments?.getParcelableArrayList("data")

        if (!pastodersList.isNullOrEmpty()) {
            adapter = PastOrdersAdapter(pastodersList!!, this)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
            rcList.layoutManager = layoutManager
            rcList.adapter = adapter
        }
    }

    override fun onClick(v: View?, data: Data) {
        val detail = Intent(activity, OrdersDetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}