package com.jquery404.foodie.main.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jquery404.foodie.R
import com.jquery404.foodie.main.models.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_cart_content.view.*

class ViewCartAdapter(private val ctx: Context) :
        RecyclerView.Adapter<ViewCartAdapter.CartViewHolder>() {
    private val menuItemList: MutableList<MenuItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewCartAdapter.CartViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.layout_cart_content, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewCartAdapter.CartViewHolder, position: Int) {
        holder.bindModel(menuItemList[position])
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    fun setItems(data: List<MenuItem>) {
        menuItemList.addAll(data)
        notifyDataSetChanged()
    }

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val ivThumb = view.ivCartThumb
        val tvTitle = view.tvCartTitle
        val tvQuantity = view.tvCartQuantity
        val tvPrice = view.tvCartPrice

        fun bindModel(menuItem: MenuItem) {
            Picasso.get().load(menuItem.thumb).into(ivThumb)
            tvTitle.text = menuItem.title
            tvQuantity.text = menuItem.id.toString()
            tvPrice.text = menuItem.price
        }

        override fun onClick(v: View) {
        }


    }
}