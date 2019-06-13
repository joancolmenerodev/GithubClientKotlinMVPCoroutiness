package com.joancolmenerodev.githubclient.feature.userInfo.presenter

import com.joancolmenerodev.githubclient.base.networking.Failure
import com.joancolmenerodev.githubclient.feature.userInfo.model.GithubUserResponse
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
                mView?.showError(it.toString())
            })
    }

    override fun attachView(view: UserInfoContract.View) {
        mView = view
    }

    override fun detachView() {
        getUserFromGithubUseCase.dispose()
        mView = null
    }

    private fun onSuccess(githubUserResponse: GithubUserResponse){
        mView?.showProgressBar(false)
        mView?.showUserInfo(githubUserResponse)
    }

    private fun onFailure(failure: Failure){
        mView?.showProgressBar(false)
        mView?.showError(failure.toString())
    }

}