package com.jquery404.foodie.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import com.jquery404.foodie.R
import com.jquery404.foodie.api.service.GalleryImageResponse
import com.jquery404.foodie.api.service.IGalleryImageApiService
import com.jquery404.foodie.custom.StarAnimationView
import com.jquery404.foodie.main.adapters.GalleryImageAdapter
import com.jquery404.foodie.main.models.AnswerItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_image_swipe.*
import java.util.*
import kotlin.concurrent.timerTask


class ImageGalleryActivity : BaseCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var galleryImageAdapter: GalleryImageAdapter
    private val displayMetrics = DisplayMetrics()
    private val valueAnimator = ValueAnimator.ofFloat(0f, -displayMetrics.heightPixels * 1f)
    private var quizDialog: AlertDialog? = null
    private var mAnimationView: StarAnimationView? = null
    private lateinit var timer: Timer
    var animStarted: Boolean = false

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
        galleryImageAdapter.notifyDataSetChanged()

        mAnimationView = findViewById(R.id.starAnimView)
        timer = Timer()


        /*valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            ivAnimate.translationY = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 1000*/
    }

    fun showDialog(id: Int, questionList: List<GalleryImageResponse.QuestionItem>) {
        //if (quizDialog == null)
        quizDialog = showQuizAlertDialog {
            isCancelable = false

            val answerItems: List<AnswerItem> = questionList[0].answerList!!

            quizTitle.text = getString(R.string.welcome_messages, questionList.size, questionList[0].title)
            optionA.text = answerItems[0].title
            optionB.text = answerItems[1].title


            radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                when (checkedId) {
                    R.id.rbOptionA -> {
                        rightAnswer = 0
                    }
                    R.id.rbOptionB -> {
                        rightAnswer = 1
                    }
                }
            }

            closeIconClickListener { }

            doneIconClickListener {
                if (answerItems[rightAnswer].isRight == 0) {
                    mAnimationView!!.onStart()
                    animStarted = true
                    timer.schedule(timerTask {
                        mAnimationView!!.onStop()
                        Thread(Runnable {
                            this@ImageGalleryActivity.runOnUiThread(java.lang.Runnable {
                                mAnimationView!!.visibility = View.GONE
                            })
                        }).start()
                    }, 3000)
                } else
                    Toast.makeText(applicationContext, "Wrong", Toast.LENGTH_LONG).show()
            }
        }
        quizDialog?.show()
    }

    private fun fetchGalleryList() {
        val apiService = IGalleryImageApiService.create()
        compositeDisposable.add(
                apiService.getGallery("4")
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ galleryImageAdapter.setGallery(it.record!!) },
                                {
                                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                                })
        )
    }

    override fun onStop() {
        compositeDisposable.clear()
        if (animStarted)
            mAnimationView!!.onStop()

        super.onStop()
    }
}