package com.example.task_todoapp.room


import androidx.room.Dao
import androidx.room.Query
import com.example.task_todoapp.model.Product

@Dao
interface Dao : BaseDao<Product> {

    @Query("SELECT * from itemToSell")
    suspend fun fetchAllProducts(): List<Product>

}