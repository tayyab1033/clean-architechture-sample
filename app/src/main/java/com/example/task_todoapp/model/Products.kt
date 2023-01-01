package com.example.task_todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class Products : ArrayList<Product>()

@Entity(tableName = "ItemToSell")
data class Product(

    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
)