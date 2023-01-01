package com.example.task_todoapp.repository

import android.content.Context
import com.example.task_todoapp.model.Product
import com.example.task_todoapp.room.AppDatabase
import com.example.task_todoapp.room.Dao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val mDao: Dao = AppDatabase.geDB(context).mDao()


    suspend fun insertProduct(list: List<Product>) {
        return mDao.insert(list)
    }

    suspend fun getData(): List<Product> {
        return mDao.fetchAllProducts()
    }

}