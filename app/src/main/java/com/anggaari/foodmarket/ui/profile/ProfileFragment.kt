package com.anggaari.foodmarket.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anggaari.foodmarket.FoodMarket
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.login.User
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.tabLayout
import kotlinx.android.synthetic.main.fragment_profile.viewPager

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val sectionPageAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        viewPager.adapter = sectionPageAdapter
        tabLayout.setupWithViewPager(viewPager)

        val user = FoodMarket.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)
        tvName.text = userResponse.name
        tvName.text = userResponse.email

        if(userResponse.profilePhotoUrl.isNotEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profilePhotoUrl)
                .circleCrop()
                .into(ivPicture)
        }

    }
}