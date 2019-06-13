package com.joancolmenerodev.githubclient.base.usecase

import com.joancolmenerodev.githubclient.base.networking.Failure
import kotlinx.coroutines.*

abstract class UseCase<Response, in Request> {

    private var job = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + job)

    @Throws(Exception::class)
    abstract suspend fun run(params: Request? = null): Response

    open fun invoke(params: Request? = null, onResult: (Response) -> Unit, onFailure: (Failure) -> Unit) {
        uiScope.launch {
            try {
                val result = withContext(Dispatchers.IO) { run(params) }
                onResult(result)
            } catch (e: Exception) {
                onFailure(Failure.Error(e.message ?: e.toString()))
            }
        }
    }

    open fun dispose() {
        job.cancel()
    }
}