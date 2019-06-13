package com.joancolmenerodev.githubclient.base.networking

sealed class Failure {

    data class Error(val errorMessage: String) : Failure()
    object NetworkConnection : Failure()
    object ServerError : Failure()

    abstract class FeatureFailure : Failure()
}