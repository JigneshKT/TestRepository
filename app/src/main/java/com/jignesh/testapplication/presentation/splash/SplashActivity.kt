package com.jignesh.testapplication.presentation.splash

import android.content.Intent
import android.os.Bundle
import com.jignesh.testapplication.R
import com.jignesh.testapplication.presentation.base.BaseActivity
import com.jignesh.testapplication.presentation.home.HomeActivity
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity<SplashPresenter, SplashView>(), SplashView {

    @Inject
    override lateinit var presenter: SplashPresenter

    override var view: SplashView = this
    override var activityLayout: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openHomeActivity()
    }

    fun openHomeActivity(){
        Timer().schedule(2000){
            val intent = HomeActivity.getIntent(this@SplashActivity)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}