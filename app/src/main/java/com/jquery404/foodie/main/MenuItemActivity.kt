package com.jquery404.foodie.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jquery404.foodie.R
import com.jquery404.foodie.api.service.IMenuCategoryApiService
import com.jquery404.foodie.api.service.IMenuItemApiService
import com.jquery404.foodie.main.adapters.MenuItemAdapter
import com.jquery404.foodie.main.adapters.MenuItemPagerAdapter
import com.jquery404.foodie.main.fragments.MenuItemFragment
import com.jquery404.foodie.main.models.MenuCategory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_menu_item.*
import kotlinx.android.synthetic.main.layout_menu_cat_tab.*

class MenuItemActivity : BaseCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var catId: String? = null
    private lateinit var menuItemAdapter: MenuItemAdapter

    private var record: MutableList<MenuCategory> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item)

        fetchCategories()
        init()
    }

    private fun initFragment() {
        val adapter = MenuItemPagerAdapter(supportFragmentManager)

        record.forEachIndexed { index, value ->
            adapter.addFragment(MenuItemFragment(), value.title.toString(), index)
        }

        vpMenuCategory.adapter = adapter
        tlCategory.setupWithViewPager(vpMenuCategory)
    }

    private fun fetchCategories() {
        val apiService = IMenuCategoryApiService.create()
        compositeDisposable.add(
                apiService.getCategories("1")
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            record.addAll(it.record)
                            initFragment()
                        }, {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                        })
        )
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
}