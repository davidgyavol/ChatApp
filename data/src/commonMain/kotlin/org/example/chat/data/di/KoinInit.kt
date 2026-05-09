package org.example.chat.data.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

expect val platformModule: Module

fun KoinApplication.getDataModules() = modules(
    databaseModule,
    dispatcherModule,
    repositoryModules,
    commonModule,
    useCaseModules,
    platformModule
)
