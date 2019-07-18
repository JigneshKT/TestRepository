package com.jignesh.testapplication.domain.usecase.base

import arrow.core.Either
import com.jignesh.testapplication.domain.models.base.Failure
import io.reactivex.Observable

interface UseCase<Type, in Params> {

    operator fun invoke(params: Params): Observable<Either<Failure, Type>>

}