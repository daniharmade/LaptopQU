package com.example.laptopqu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.laptopqu.data.LaptopRepository
import com.example.laptopqu.model.Laptop

class LaptopDetailViewModel(private val repository: LaptopRepository) : ViewModel() {

    fun getLaptopById(laptopId: Int): Laptop? {
        // Get laptop by its ID from the repository
        return repository.getLaptopById(laptopId)
    }

    // Factory to create ViewModel with repository
    class Factory(private val repository: LaptopRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LaptopDetailViewModel(repository) as T
        }
    }
}
