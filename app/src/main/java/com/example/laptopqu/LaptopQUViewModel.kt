package com.example.laptopqu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.laptopqu.data.LaptopRepository
import com.example.laptopqu.model.Laptop
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LaptopQUViewModel(private val repository: LaptopRepository) : ViewModel() {
    private val _groupedLaptops = MutableStateFlow(
        repository.getLaptops()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedLaptops: StateFlow<Map<Char, List<Laptop>>> get() = _groupedLaptops

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedLaptops.value = repository.searchLaptops(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}


class ViewModelFactory(private val repository: LaptopRepository) :
    androidx.lifecycle.ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaptopQUViewModel::class.java)) {
            return LaptopQUViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}