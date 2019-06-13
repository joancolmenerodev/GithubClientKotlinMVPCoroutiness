package com.joancolmenerodev.githubclient.feature.userInfo.di

import com.joancolmenerodev.githubclient.feature.userInfo.presenter.UserFromGithubPresenterImpl
import com.joancolmenerodev.githubclient.feature.userInfo.presenter.UserInfoContract
import com.joancolmenerodev.githubclient.feature.userInfo.repository.GithubUserRepositoryImpl
import com.joancolmenerodev.githubclient.feature.userInfo.usecases.GetUserFromGithubUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val userInfoModule = Kodein.Module("UserInfoModule" ){

    bind<UserInfoContract.Presenter>() with singleton {
        UserFromGithubPresenterImpl(instance())
    }
    bind<GetUserFromGithubUseCase>() with singleton {
        GetUserFromGithubUseCase(instance())
    }
    bind<GithubUserRepositoryImpl>() with singleton {
        GithubUserRepositoryImpl(instance())
    }
}