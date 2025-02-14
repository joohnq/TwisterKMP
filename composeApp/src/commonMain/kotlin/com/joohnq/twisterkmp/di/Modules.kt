package com.joohnq.twisterkmp.di

import com.joohnq.twisterkmp.TwisterViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val viewModelModule = module {
    viewModelOf(::TwisterViewModel)
}

val sharedModule = module {

}
