package com.xavrs.di

import com.xavrs.repository.HelloRepository
import com.xavrs.service.HelloService
import com.xavrs.service.HelloServiceImpl
import org.koin.dsl.module.module

val myModule = module {
    single { HelloServiceImpl(get()) as HelloService }
    single { HelloRepository() }
}