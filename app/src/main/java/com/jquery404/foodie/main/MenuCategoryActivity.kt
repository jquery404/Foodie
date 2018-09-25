package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jquery404.foodie.R
import com.jquery404.foodie.api.service.IMenuCategoryApiService
import com.jquery404.foodie.main.adapters.MenuCategoryAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_menu_categories.*


class MenuCategoryActivity : BaseCompatActivity() {


    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var menuCategoryAdapter: MenuCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_categories)
        init()
    }

    private fun init() {

        menuCategoryAdapter = MenuCategoryAdapter(this)
        rvMenuCat.layoutManager = LinearLayoutManager(this)
        rvMenuCat.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvMenuCat.adapter = menuCategoryAdapter

        fetchCategories()
    }

    private fun fetchCategories() {
        val apiService = IMenuCategoryApiService.create()
        compositeDisposable.add(
                apiService.getMovies()
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ menuCategoryAdapter.setMovies(it.data) },
                                {
                                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                                })
        )
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MenuCategoryActivity::class.java)
            intent.putExtra("flag", context.javaClass.getSimpleName())
            context.startActivity(intent)
        }
    }
}
