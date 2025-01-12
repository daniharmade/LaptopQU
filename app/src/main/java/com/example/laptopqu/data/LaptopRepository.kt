package com.example.laptopqu.data

import com.example.laptopqu.model.Laptop
import com.example.laptopqu.model.LaptopData

class LaptopRepository {
    fun getLaptops(): List<Laptop> {
        return LaptopData.laptop
    }

    fun searchLaptops(query: String): List<Laptop>{
        return LaptopData.laptop.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}