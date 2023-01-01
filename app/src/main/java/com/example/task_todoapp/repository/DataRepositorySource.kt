package com.example.task_todoapp.repository

import com.example.task_todoapp.model.Persons
import com.example.task_todoapp.model.Products
import com.example.task_todoapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestProducts(): Flow<Resource<Products>>
    suspend fun requestPersons(): Flow<Resource<Persons>>
}