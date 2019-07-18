package com.jignesh.testapplication.domain.usecase.location

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import android.location.Location
import com.jignesh.testapplication.domain.models.base.CallInfo
import com.jignesh.testapplication.domain.models.base.Failure
import com.jignesh.testapplication.domain.repository.LocationRespository
import com.jignesh.testapplication.domain.repository.WeatherRepository
import com.jignesh.testapplication.domain.usecase.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
private val locationRespository: LocationRespository
) : UseCase<Location, GetLocationUseCase.Params > {

    override fun invoke(params: Params): Observable<Either<Failure, Location>> =
            locationRespository.getLocationUpdates()


    class Params()

}