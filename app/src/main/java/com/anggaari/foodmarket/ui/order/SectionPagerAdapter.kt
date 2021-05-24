package com.anggaari.foodmarket.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.anggaari.foodmarket.model.response.transaction.Data
import com.anggaari.foodmarket.ui.order.inprogress.InprogressFragment
import com.anggaari.foodmarket.ui.order.pastorder.PastOrderFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var inprogressList: ArrayList<Data>? = ArrayList()
    var pastOrdersList: ArrayList<Data>? = ArrayList()

    override fun getItem(position: Int): Fragment {
        val fragment : Fragment
        when (position) {
            0 -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.setArguments(bundle)
                return fragment
            }
            1 -> {
                fragment = PastOrderFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastOrdersList)
                fragment.setArguments(bundle)
                return fragment
            }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.setArguments(bundle)
                return fragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "In Progress"
            1 -> "Past Orders"
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    fun setData(inProgressListParams: ArrayList<Data>?, pastOrdersListParams: ArrayList<Data>?) {
        inprogressList = inProgressListParams
        pastOrdersList = pastOrdersListParams
    }
}