package com.jignesh.testapplication.domain.models.base

interface ResponseObject<out DomainObject : Any> {

    fun toAppDomain(): DomainObject

}