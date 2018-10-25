package com.xavrs.di

import com.xavrs.repository.MessageRepository
import com.xavrs.service.MessageService
import com.xavrs.service.MessageServiceImpl
import org.koin.dsl.module.module

val myModule = module {
    single { MessageServiceImpl(get()) as MessageService }
    single { MessageRepository() }
}
