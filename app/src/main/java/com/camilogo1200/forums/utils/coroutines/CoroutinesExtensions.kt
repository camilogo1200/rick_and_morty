package com.camilogo1200.forums.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import retrofit2.Response

const val MAX_RETRY = 3
const val AWAIT_RETRY_TIME = 200L

suspend fun <T> networkCall(
    coroutineDispatcher: CoroutineDispatcher,
    apiCallMethod: suspend (Int?) -> Response<T>,
    nextPage: Int?
): Response<T> {
    val retry = 0
    var response: Response<T> = Response.success(null)
    return runCatching {
        withContext(coroutineDispatcher) {
            ensureActive()
            while (retry <= MAX_RETRY) {
                response = apiCallMethod.invoke(nextPage)
                if (response.isSuccessful) break
                delay(AWAIT_RETRY_TIME)
            }
            response
        }
    }.getOrThrow()
}

