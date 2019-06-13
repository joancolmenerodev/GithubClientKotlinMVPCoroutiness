package com.joancolmenerodev.githubclient.feature.userInfo.presenter

import com.joancolmenerodev.githubclient.feature.userInfo.usecases.GetUserFromGithubUseCase

class UserFromGithubPresenterImpl(private val getUserFromGithubUseCase: GetUserFromGithubUseCase) :
    UserInfoContract.Presenter {

    private var mView: UserInfoContract.View? = null

    override fun findUser(username: String) {
        mView?.showProgressBar(true)
        getUserFromGithubUseCase.invoke(
            username,
            {
                mView?.showProgressBar(false)
                mView?.showUserInfo(it)
            },
            {
                mView?.showProgressBar(false)
                mView?.setUserErrorLayoutVisible()
                mView?.showError(it.toString())
            })
    }

    override fun attachView(view: UserInfoContract.View) {
        mView = view
    }

    override fun onItemClicked(username: String) {
        mView?.navigateToDetails(username)
    }

    override fun detachView() {
        getUserFromGithubUseCase.dispose()
        mView = null
    }


}