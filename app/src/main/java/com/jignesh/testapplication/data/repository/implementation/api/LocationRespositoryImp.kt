package com.jignesh.testapplication.data.repository.implementation.api

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.annotation.Nullable
import arrow.core.Either
import arrow.core.Right
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener
import com.jignesh.testapplication.data.net.WeatherApiClient
import com.jignesh.testapplication.data.repository.base.BaseRepository
import com.jignesh.testapplication.domain.models.base.Failure
import com.jignesh.testapplication.domain.models.weather.Weather
import com.jignesh.testapplication.domain.repository.LocationRespository
import com.jignesh.testapplication.domain.repository.WeatherRepository
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LocationRepositoryImp  @Inject constructor(
    context : Context
) : LocationCallback(), LocationRespository, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var locationRequest:LocationRequest
    var fusedLocationProviderClient:FusedLocationProviderClient
    var googleApiClient : GoogleApiClient
    val subject : PublishSubject<Either<Failure, Location>> = PublishSubject.create<Either<Failure, Location>>()
    var subscriberCount = 0

    init {
        googleApiClient = GoogleApiClient.Builder(context)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        googleApiClient.connect()

        locationRequest = LocationRequest()
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context) //initiate in onCreate

    }



    override  fun getLocationUpdates(): Observable<Either<Failure, Location>> {
        return subject.doOnSubscribe { disposable ->
            if (subscriberCount == 0) {
                startLocationUpdates()
            }
            subscriberCount += 1
        }.doOnDispose {
            subscriberCount -= 1
            if (subscriberCount == 0) {
                stopLocationUpdates()
            }
        }
    }


    @SuppressLint("MissingPermission")
    override fun onConnected(@Nullable bundle: Bundle?) {
        //        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                subject.onNext(Right(location))
            }
        }


        if (subscriberCount > 0) {
            startLocationUpdates()
        }
    }


    override fun onConnectionFailed(@NonNull connectionResult: ConnectionResult) {
        subject.onError(Throwable(connectionResult.errorMessage))
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (googleApiClient != null && googleApiClient.isConnected) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, this, Looper.getMainLooper())
        }
    }

    private fun stopLocationUpdates() {
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(this)
        }
    }

    override fun onLocationResult(locationResult: LocationResult?) {
        if (locationResult == null) {
            return
        }
        for (location in locationResult.locations) {
            if (location != null) {
                subject.onNext(Right(location))
            }
        }
    }

}