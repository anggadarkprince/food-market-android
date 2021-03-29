package com.anggaari.foodmarket.ui.home.popular

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.dummy.HomeVerticalModel
import com.anggaari.foodmarket.ui.detail.DetailActivity
import com.anggaari.foodmarket.ui.home.newtaste.HomeNewTasteAdapter
import kotlinx.android.synthetic.main.fragment_home_new_taste.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomePopularFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePopularFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {
    private var foodList: ArrayList<HomeVerticalModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()

        var adapter = HomeNewTasteAdapter(foodList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeVerticalModel("Sate Ayam", "10000", "", 4f))
        foodList.add(HomeVerticalModel("Burger Tamayo", "15000", "", 5f))
        foodList.add(HomeVerticalModel("Nasi Goreng", "20000", "", 3.5f))
        foodList.add(HomeVerticalModel("Cherry Healty", "10000", "", 4f))
        foodList.add(HomeVerticalModel("Burger Tamayo", "15000", "", 5f))
        foodList.add(HomeVerticalModel("Nasi Goreng", "20000", "", 3.5f))
    }

    override fun onClick(v: View, data: HomeVerticalModel) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }

}