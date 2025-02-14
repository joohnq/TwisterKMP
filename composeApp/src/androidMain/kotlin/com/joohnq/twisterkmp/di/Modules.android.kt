package com.joohnq.twisterkmp.di

import com.joohnq.twisterkmp.GeneralInfo
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::GeneralInfo)
}
