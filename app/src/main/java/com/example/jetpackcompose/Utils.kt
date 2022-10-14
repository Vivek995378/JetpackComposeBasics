package com.example.jetpackcompose

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.ui.graphics.Color

// Models
data class Person(
    val name: String,
    val age: Int,
    val profilePictureUrl: String? = null
)

data class Amenity(
    val name: String,
    val iconUrl: String
)

// Methods
fun getPersonList() = listOf(
    Person("Ritika", 25),
    Person("Yatin", 29),
    Person("Manish", 28),
    Person("Vaibhav", 41),
    Person("Ankit", 31),
    Person("Vivek", 42),
    Person("Raghav", 91),
    Person("Vishal", 22),
    Person("Adda247", 1),
    Person("Android", 9),
    Person("Jetpack", 2),
    Person("Compose", 35)
)

val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)
