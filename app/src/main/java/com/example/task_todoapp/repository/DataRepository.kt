package com.example.task_todoapp.repository

import com.example.task_todoapp.model.Persons
import com.example.task_todoapp.model.Products
import com.example.task_todoapp.remote.RemoteData
import com.example.task_todoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData
) : DataRepositorySource {
    override suspend fun requestProducts(): Flow<Resource<Products>> {
        return flow {
            emit(remoteRepository.requestProducts())
        }
    }

    override suspend fun requestPersons(): Flow<Resource<Persons>> {
        return flow {
            emit(remoteRepository.requestPersons())
        }
    }
}