package com.joancolmenerodev.githubclient.di

import android.app.Application
import com.joancolmenerodev.githubclient.feature.userInfo.di.userInfoModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(retrofitModule)
        import(userInfoModule)
    }
}