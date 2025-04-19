package com.example.ph55500.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ph55500.model.Cat
import com.example.ph55500.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {
    private val _cats = MutableStateFlow<List<Cat>>(emptyList())
    val cats: StateFlow<List<Cat>> = _cats

    private val _selectedCat = MutableStateFlow<Cat?>(null)
    val selectedCat: StateFlow<Cat?> = _selectedCat

    init {
        fetchCats()
    }

    fun fetchCats() {
        viewModelScope.launch {
            try {
                _cats.value = RetrofitInstance.api.getCats()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun selectCat(cat: Cat) {
        _selectedCat.value = cat
    }

    fun dismissDialog() {
        _selectedCat.value = null
    }
}
