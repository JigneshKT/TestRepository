package com.jignesh.testapplication.presentation.home

import com.jignesh.testapplication.domain.extensions.check
import com.jignesh.testapplication.domain.usecase.location.GetLocationUseCase
import com.jignesh.testapplication.domain.usecase.weather.GetFiveDaysWeatherUseCase
import com.jignesh.testapplication.domain.usecase.weather.GetWeatherUseCase
import com.jignesh.testapplication.presentation.base.Presenter
import com.jignesh.testapplication.presentation.splash.SplashView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getFiveDaysWeatherUseCase: GetFiveDaysWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : Presenter<HomeView>() {

    private val locationDisposable : CompositeDisposable = CompositeDisposable();

    fun getWeather( lat: String, lon : String, appid: String ){
        addSubscription(
            getWeatherUseCase(GetWeatherUseCase.Params(lat, lon, appid)).subscribe { either ->
                either.check(
                    { view?.getWeatherFailure() },
                    { view?.getWeatherSuccess(it) }
                )
            }
        )
    }

    fun getFiveDaysWeather( lat: String, lon : String, appid: String ){
        addSubscription(
            getFiveDaysWeatherUseCase(GetFiveDaysWeatherUseCase.Params(lat, lon, appid)).subscribe { either ->
                either.check(
                    { view?.getFiveDaysWeatherFailure() },
                    { view?.getFiveDaysWeatherSuccess(it) }
                )
            }
        )
    }


    fun getLocation(){
        locationDisposable.add(
            getLocationUseCase(GetLocationUseCase.Params()).subscribe { either ->
                either.check(
                    { view?.onLocationFailure() },
                    { view?.onLocationSuccess(it) }
                )
            }
        )
    }
}