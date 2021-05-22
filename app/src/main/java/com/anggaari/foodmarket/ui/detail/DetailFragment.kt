package com.anggaari.foodmarket.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.home.Data
import com.anggaari.foodmarket.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    var bundle:Bundle?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        Log.e("data", arguments.toString());
        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }

        btnOrderNow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_payment, bundle)
        }
    }

    private fun initView(data: Data?) {
        bundle = bundleOf("data" to data)

        Glide.with(requireContext())
            .load(data?.imageUrl)
            .into(ivPoster)

        tvTitle.text = data?.foodName
        tvDescription.text = data?.description
        tvIngredient.text = data?.ingredients
        tvTotal.formatPrice(data?.price.toString())
    }
}