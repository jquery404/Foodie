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
import com.jquery404.foodie.main.MenuItemActivity
import com.jquery404.foodie.main.models.MenuCategory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_menu_cat_content.view.*

class MenuCategoryAdapter(private val ctx: Context) :
        RecyclerView.Adapter<MenuCategoryAdapter.CategoryViewHolder>() {

    private val menuCategoryList: MutableList<MenuCategory> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoryAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.layout_menu_cat_content, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuCategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bindModel(menuCategoryList[position])
    }

    override fun getItemCount(): Int {
        return menuCategoryList.size
    }

    fun setCategories(record: List<MenuCategory>) {
        menuCategoryList.addAll(record)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val tvTitle = view.tvCatTitle
        val ivThumb = view.ivCatThumb
        var id: Int? = null

        init {
            view.setOnClickListener(this)
        }

        fun bindModel(menuCategory: MenuCategory) {
            tvTitle.text = menuCategory.title
            Picasso.get().load(menuCategory.thumb).into(ivThumb)
            id = menuCategory.id
        }

        override fun onClick(v: View) {
            val extras = Bundle()
            extras.putString("CAT_ID", id.toString())
            ActivityHelper.start(v.context, MenuItemActivity::class.java, extras)
        }

    }


}

