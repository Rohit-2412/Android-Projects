package com.example.todolist

// Creating a class to hold the data of item in the form of a string and a boolean value
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)