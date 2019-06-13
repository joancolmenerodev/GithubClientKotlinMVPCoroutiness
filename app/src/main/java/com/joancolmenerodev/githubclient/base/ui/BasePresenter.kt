package com.joancolmenerodev.githubclient.base.ui

/**
 * Created by joancolmenero on 11,June,2019
 **/
interface BasePresenter<in T : BaseView> {
    fun attachView(view: T)
    fun detachView()
}