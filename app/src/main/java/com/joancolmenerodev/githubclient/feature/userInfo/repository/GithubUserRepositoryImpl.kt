package com.joancolmenerodev.githubclient.feature.userInfo.repository

import com.joancolmenerodev.githubclient.feature.userInfo.model.GithubUserResponse
import com.joancolmenerodev.githubclient.service.GithubService

class GithubUserRepositoryImpl(var githubService: GithubService) {

    suspend fun getUserFromGithub(username: String) : GithubUserResponse {
        return githubService.getGithubUser(username)
    }
}