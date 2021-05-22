package com.anggaari.foodmarket.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anggaari.foodmarket.FoodMarket
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.home.Data
import com.anggaari.foodmarket.model.response.home.HomeResponse
import com.anggaari.foodmarket.model.response.login.User
import com.anggaari.foodmarket.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {
    private var newTasteList: ArrayList<Data>? = ArrayList()
    private var popularList: ArrayList<Data>? = ArrayList()
    private var recommendedList: ArrayList<Data>? = ArrayList()
    private var progressDialog: Dialog? = null
    private var adapter: HomeAdapter? = null

    private lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = HomePresenter(this)
        presenter.getHome()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        val user = FoodMarket.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)
        if (userResponse.profilePhotoUrl.isNotEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profilePhotoUrl)
                .into(ivProfile)
        }
    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        for (a in homeResponse.data.indices) {
            val items: List<String> = homeResponse.data[a].types!!.split(",")
            for (x in items.indices) {
                when {
                    items[x].equals("new_food", true) -> {
                        newTasteList?.add(homeResponse.data[a])
                    }
                    items[x].equals("recommended", true) -> {
                        recommendedList?.add(homeResponse.data[a])
                    }
                    items[x].equals("popular", true) -> {
                        popularList?.add(homeResponse.data[a])
                    }
                }
            }
        }

        adapter = HomeAdapter(homeResponse.data, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        val sectionsPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionsPagerAdapter.setData(newTasteList, popularList, recommendedList)
        viewPager!!.offscreenPageLimit = 3
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}