package com.joohnq.twisterkmp.di

import com.joohnq.twisterkmp.viewmodel.ViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformModule: Module

val viewModelModule = module {
    singleOf(::ViewModel)
}

val sharedModule = module {

}
