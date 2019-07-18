package com.jignesh.testapplication.domain.repository

import android.location.Location
import arrow.core.Either
import com.jignesh.testapplication.domain.models.base.Failure
import io.reactivex.Observable

interface LocationRespository {
    fun getLocationUpdates(): Observable<Either<Failure, Location>>
}