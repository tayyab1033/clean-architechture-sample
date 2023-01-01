package com.example.task_todoapp.remote

import com.example.task_todoapp.model.Persons
import com.example.task_todoapp.model.Products
import com.example.task_todoapp.utils.Resource

internal interface RemoteDataSource {
    suspend fun requestProducts(): Resource<Products>
    suspend fun requestPersons(): Resource<Persons>
}