package com.jignesh.testapplication.presentation.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jignesh.testapplication.R
import com.jignesh.testapplication.domain.models.weather.Weather
import com.jignesh.testapplication.presentation.base.BaseActivity
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject

@RuntimePermissions
class HomeActivity : BaseActivity<HomePresenter, HomeView>(), HomeView {
    @Inject
    override lateinit var presenter: HomePresenter
    override var view: HomeView = this
    override var activityLayout: Int = R.layout.activity_home

    private var recyclerView: RecyclerView? = null
    private lateinit var homeAdapter : HomeAdapter

    companion object {
        fun getIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.recyclerView)
//        presenter.getWeather("23.028520","72.600230","b9333b2d0dccfd44291ef74b06ceb5d9")
        startListeningLocationWithPermissionCheck();
    }

    override fun getWeatherSuccess(weather: Weather) {
        Log.e("Weather","Success")
    }

    override fun getWeatherFailure() {
        Log.e("Weather","Failure")
    }

    override fun getFiveDaysWeatherSuccess(fiveDaysWeather: List<Weather>) {
        Log.e("Weather","Success")
        homeAdapter = HomeAdapter(fiveDaysWeather,this)
        recyclerView?.adapter = homeAdapter
        homeAdapter.setOnItemClickListener(object : HomeAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                showToast("Position clicked ${pos}")
            }
        })
    }

    override fun getFiveDaysWeatherFailure() {
        Log.e("Weather","Failure")
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun startListeningLocation() {
        presenter.getLocation()
    }


    override fun onLocationSuccess(location: Location) {
        Log.e("Weather","Location:::" + location.latitude + " " + location.longitude)
        presenter.getFiveDaysWeather(""+location.latitude,""+location.longitude,"b9333b2d0dccfd44291ef74b06ceb5d9")
    }

    override fun onLocationFailure() {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated function
        onRequestPermissionsResult(requestCode, grantResults)
    }
}