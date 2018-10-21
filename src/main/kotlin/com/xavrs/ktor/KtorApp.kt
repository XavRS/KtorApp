package com.xavrs.ktor

import com.xavrs.di.myModule
import com.xavrs.service.HelloService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.ktor.ext.inject

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
}

fun Application.routes(){
    val service: HelloService by inject()

    routing {
        get("/hello") {
            call.respondText(service.sayHelloWorld())
        }
    }
}

fun main(args: Array<String>) {
    startKoin(listOf(myModule))
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}
