package com.jubutech.grocery_2402

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GroceryRepository
    val allGroceries: LiveData<List<GroceryItem>>

    init {
        val groceryDao = GroceryDatabase.getDatabase(application).groceryDao()
        repository = GroceryRepository(groceryDao)
        allGroceries = repository.allGroceries
    }

    fun insert(item: GroceryItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun delete(item: GroceryItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }
}
