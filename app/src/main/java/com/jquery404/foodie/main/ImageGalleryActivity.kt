package com.jquery404.foodie.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.VelocityTracker
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import com.jquery404.foodie.R
import com.jquery404.foodie.api.service.IGalleryImageApiService
import com.jquery404.foodie.main.adapters.GalleryImageAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_image_swipe.*

class ImageGalleryActivity : BaseCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var galleryImageAdapter: GalleryImageAdapter
    private val displayMetrics = DisplayMetrics()
    private val valueAnimator = ValueAnimator.ofFloat(0f, -displayMetrics.heightPixels*1f)

    private companion object Params {
        val INITIAL_SCALE = 1f
        val STIFFNESS = SpringForce.STIFFNESS_MEDIUM
        val DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    }

    lateinit var scaleXAnimation: SpringAnimation
    lateinit var scaleYAnimation: SpringAnimation
    lateinit var jumpingYAnimation: SpringAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_swipe)
        init()
        fetchGalleryList()
    }

    fun init() {
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        galleryImageAdapter = GalleryImageAdapter(this)
        rvImageGallery.layoutManager = LinearLayoutManager(this)
        rvImageGallery.adapter = galleryImageAdapter

        /*scaleXAnimation = createSpringAnimation(
                ivAnimate, SpringAnimation.SCALE_X,
                INITIAL_SCALE, STIFFNESS, DAMPING_RATIO)
        scaleYAnimation = createSpringAnimation(
                ivAnimate, SpringAnimation.SCALE_Y,
                INITIAL_SCALE, STIFFNESS, DAMPING_RATIO)

        jumpingYAnimation = createSpringAnimation(
                ivAnimate, SpringAnimation.TRANSLATION_Y,
                displayMetrics.heightPixels / 2f, STIFFNESS, DAMPING_RATIO)*/

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            ivAnimate.translationY = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 1000
    }

    private fun createSpringAnimation(view: View,
                                      property: DynamicAnimation.ViewProperty,
                                      finalPosition: Float,
                                      stiffness: Float,
                                      dampingRatio: Float): SpringAnimation {
        val animation = SpringAnimation(view, property)
        val spring = SpringForce(finalPosition)
        spring.stiffness = stiffness
        spring.dampingRatio = dampingRatio
        animation.spring = spring
        return animation
    }

    fun animatingNow() {

        ivAnimate.visibility = View.VISIBLE
        valueAnimator.start()
    }

    private fun fetchGalleryList() {
        val apiService = IGalleryImageApiService.create()
        compositeDisposable.add(
                apiService.getGallery("3")
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ galleryImageAdapter.setGallery(it.record) },
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