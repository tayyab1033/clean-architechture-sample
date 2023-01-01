package com.example.task_todoapp.network

import com.example.task_todoapp.model.Persons
import com.example.task_todoapp.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface DemoApiInterface {
    @GET("abc/demo-1/call") // replace abc with your own BASE_URL
    suspend fun fetchCallList(): Response<Persons>

    @GET("abc/demo-1/buy") // replace abc with your own BASE_URL
    suspend fun fetchBuyList(): Response<Products>
}