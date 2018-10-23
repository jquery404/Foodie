package com.jquery404.foodie.main.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jquery404.foodie.R
import com.jquery404.foodie.api.service.GalleryImageResponse
import com.jquery404.foodie.main.ImageGalleryActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_menu_cat_content.view.*

class GalleryImageAdapter(private val ctx: Context) :
        RecyclerView.Adapter<GalleryImageAdapter.GalleryViewHolder>() {

    private val galleryItemList: MutableList<GalleryImageResponse.GalleryItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageAdapter.GalleryViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.layout_menu_cat_content, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryImageAdapter.GalleryViewHolder, position: Int) {
        holder.bindModel(galleryItemList[position])
    }

    override fun getItemCount(): Int {
        return galleryItemList.size
    }

    fun setGallery(record: List<GalleryImageResponse.GalleryItem>) {
        galleryItemList.addAll(record)
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val tvTitle = view.tvCatTitle
        val ivThumb = view.ivCatThumb
        var id: Int? = null
        var questionList: List<GalleryImageResponse.QuestionItem>? = null

        init {
            view.setOnClickListener(this)
        }

        fun bindModel(galleryItem: GalleryImageResponse.GalleryItem) {
            tvTitle.text = galleryItem.title
            Picasso.get().load(galleryItem.thumb).into(ivThumb)
            id = galleryItem.id
            questionList = galleryItem.questionList
        }

        override fun onClick(v: View) {
            (ctx as ImageGalleryActivity).showDialog(questionList!!.size, questionList!!)
        }

    }
}