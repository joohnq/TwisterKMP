package com.joohnq.twisterkmp

import android.app.Application
import com.joohnq.twisterkmp.di.KoinInitializer

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(this).init()
    }
}