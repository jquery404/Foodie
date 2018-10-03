package com.jquery404.foodie.main.adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jquery404.foodie.R
import com.jquery404.foodie.helpers.ActivityHelper
import com.jquery404.foodie.main.MenuDetailsActivity
import com.jquery404.foodie.main.models.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_menu_item_content.view.*

class MenuItemAdapter(private val ctx: Context) :
        RecyclerView.Adapter<MenuItemAdapter.ItemViewHolder>() {

    private val menuItemList: MutableList<MenuItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemAdapter.ItemViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.layout_menu_item_content, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemAdapter.ItemViewHolder, position: Int) {
        holder.bindModel(menuItemList[position])
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    fun setItems(data: List<MenuItem>) {
        menuItemList.addAll(data)
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val ivThumb = view.ivFoodThumb
        val tvTitle = view.tvFoodTitle
        val tvPrice = view.tvFoodPrice
        var imgThumb: String? = null
        var foodDesc: String? = null

        init {
            view.setOnClickListener(this)
        }

        fun bindModel(menuItem: MenuItem) {
            Picasso.get().load(menuItem.thumb).into(ivThumb)
            tvTitle.text = menuItem.title
            tvPrice.text = menuItem.price
            imgThumb = menuItem.thumb
            foodDesc = menuItem.description
        }

        override fun onClick(view: View) {
            val extras = Bundle()
            extras.putString("IMAGE_PATH", imgThumb)
            extras.putString("DESCRIPTION", foodDesc)
            ActivityHelper.start(view.context, MenuDetailsActivity::class.java, extras)
        }

    }
}