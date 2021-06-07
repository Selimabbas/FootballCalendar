package com.project.selim.footcalendar.data

import com.project.selim.footcalendar.data.network.ApiResult
import com.project.selim.footcalendar.data.network.FootApi
import com.project.selim.footcalendar.data.network.createNetwork
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    protected val footApi: FootApi by lazy {
        createNetwork()
    }

    fun <T> apiCall(response: Response<T>): ApiResult<T> {
        if (response.isSuccessful)
            response.body()?.let {
                return ApiResult.Success(it)
            }

        return ApiResult.Error(IOException("error"))
    }
}
