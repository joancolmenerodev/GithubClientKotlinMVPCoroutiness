package com.joancolmenerodev.githubclient.base.ui

/**
 * Created by joancolmenero on 11,June,2019
 **/
interface BaseView {
    fun showError(errorMessage: String)
    fun showProgressBar(isVisible: Boolean)
}
