package com.joancolmenerodev.githubclient.service

import com.joancolmenerodev.githubclient.feature.userInfo.model.GithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}")
    suspend fun getGithubUser(@Path("username") username: String): GithubUserResponse

}