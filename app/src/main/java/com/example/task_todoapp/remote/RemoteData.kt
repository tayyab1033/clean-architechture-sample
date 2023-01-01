package com.example.task_todoapp.remote

import com.example.task_todoapp.error.NETWORK_ERROR
import com.example.task_todoapp.error.NO_INTERNET_CONNECTION
import com.example.task_todoapp.model.Persons
import com.example.task_todoapp.model.Products
import com.example.task_todoapp.network.DemoApiInterface
import com.example.task_todoapp.utils.NetworkConnectivity
import com.example.task_todoapp.utils.Resource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {
    override suspend fun requestProducts(): Resource<Products> {
        val service = serviceGenerator.createService(DemoApiInterface::class.java)
        return when (val response = processCall(service::fetchBuyList)) {
            is Products -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestPersons(): Resource<Persons> {
        val service = serviceGenerator.createService(DemoApiInterface::class.java)
        return when (val response = processCall(service::fetchCallList)) {
            is Persons -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}