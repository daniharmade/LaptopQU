package com.example.laptopqu.model

data class Laptop (
    val id: Int,
    val name: String,
    val photoUrl: Int,
    val specification: String,
    val price: String,
    var isFavorite: Boolean = false
)