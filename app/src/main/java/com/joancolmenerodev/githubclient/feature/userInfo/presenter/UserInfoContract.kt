package com.joancolmenerodev.githubclient.feature.userInfo.presenter

import com.joancolmenerodev.githubclient.base.ui.BasePresenter
import com.joancolmenerodev.githubclient.base.ui.BaseView
import com.joancolmenerodev.githubclient.feature.userInfo.model.GithubUserResponse

interface UserInfoContract {

    interface View : BaseView {
        fun showUserInfo(githubUserResponse: GithubUserResponse)
        fun setUserErrorLayoutVisible()
        fun navigateToDetails(login: String)
    }

    interface Presenter : BasePresenter<View> {
        fun findUser(username: String)
        fun onItemClicked(username: String)
    }
}