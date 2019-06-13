package com.joancolmenerodev.githubclient.feature.userInfo.usecases

import com.joancolmenerodev.githubclient.base.usecase.UseCase
import com.joancolmenerodev.githubclient.feature.userInfo.model.GithubUserResponse
import com.joancolmenerodev.githubclient.feature.userInfo.repository.GithubUserRepositoryImpl

class GetUserFromGithubUseCase(private val githubUserRepositoryImpl: GithubUserRepositoryImpl): UseCase<GithubUserResponse,String>(){

    override suspend fun run(params: String?): GithubUserResponse {
        params.let {
            return githubUserRepositoryImpl.getUserFromGithub(params!!)
        }
    }

}