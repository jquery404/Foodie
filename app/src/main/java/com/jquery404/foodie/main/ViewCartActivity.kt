package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.jquery404.foodie.R
import com.jquery404.foodie.api.service.IMenuItemApiService
import com.jquery404.foodie.helpers.ActivityHelper
import com.jquery404.foodie.main.adapters.ViewCartAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_view_cart.*

class ViewCartActivity : BaseCompatActivity(), View.OnClickListener {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var viewCartAdapter: ViewCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_cart)

        init()
    }

    private fun init() {
        btnPlaceOrder.setOnClickListener(this)

        viewCartAdapter = ViewCartAdapter(this)
        rvViewCart.layoutManager = LinearLayoutManager(this)
        rvViewCart.adapter = viewCartAdapter

        fetchMenuList()
    }

    private fun fetchMenuList() {
        val apiService = IMenuItemApiService.create()
        compositeDisposable.add(
                apiService.getItems("2", "1")
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ viewCartAdapter.setItems(it.record) },
                                {
                                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                                })
        )
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnPlaceOrder -> {
                ActivityHelper.start(this, OrderConfirmationActivity::class.java)
            }
        }
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

}