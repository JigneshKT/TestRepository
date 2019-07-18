package com.jignesh.testapplication.domain.models.base

sealed class Success(open val info: CallInfo) {

    class None(i: CallInfo) : Success(i)

}