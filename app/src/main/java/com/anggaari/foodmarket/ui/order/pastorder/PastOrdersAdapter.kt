package com.anggaari.foodmarket.ui.order.inprogress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.transaction.Data
import com.anggaari.foodmarket.utils.Cons
import com.anggaari.foodmarket.utils.Helpers.convertLongToTime
import com.anggaari.foodmarket.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pastoders.view.*

class PastOrdersAdapter(private val listData: ArrayList<Data>,
                        private val itemAdapterCallback: ItemAdapterCallback
) :
    RecyclerView.Adapter<PastOrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastoders, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : Data, itemAdapterCallback : ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.food.foodName
                tvDate.text = data.food.createdAt.convertLongToTime(Cons.DATE_FORMAT_CHECK_IN)
                tvTitle.text = data.food.foodName
                tvPrice.formatPrice(data.food.price.toString())

                Glide.with(context)
                    .load(data.food.imageUrl)
                    .into(ivPoster)

                if (data.status.equals("CANCELLED", true)) {
                    tvCancelled.visibility = View.VISIBLE
                }

                itemView.setOnClickListener { view -> itemAdapterCallback.onClick(view, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View?, data: Data)
    }
}