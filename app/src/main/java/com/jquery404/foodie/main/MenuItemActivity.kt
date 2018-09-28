package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jquery404.foodie.R
import com.jquery404.foodie.api.service.IMenuItemApiService
import com.jquery404.foodie.main.adapters.MenuItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_menu_item.*

class MenuItemActivity : BaseCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var catId: String? = null
    private lateinit var menuItemAdapter: MenuItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item)
        init()
    }

    private fun init() {
        catId = intent.extras.getString("CAT_ID", "")

        menuItemAdapter = MenuItemAdapter(this)
        rvMenuItem.layoutManager = LinearLayoutManager(this)
        rvMenuItem.adapter = menuItemAdapter

        fetchMenuList()
    }

    private fun fetchMenuList() {
        val apiService = IMenuItemApiService.create()
        compositeDisposable.add(
                apiService.getItems("2", catId.toString())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ menuItemAdapter.setItems(it.record) },
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
        fun start(context: Context, extras: Bundle? = null) {
            val intent = Intent(context, MenuItemActivity::class.java)
            extras?.let {
                intent.putExtras(extras)
            } ?: run {
                intent.putExtra("flag", context.javaClass.getSimpleName())
            }

            context.startActivity(intent)
        }
    }
}