package com.example.task_todoapp.model

class Persons : ArrayList<Person>()

data class Person(
    val id: Int,
    val name: String,
    val number: String
)