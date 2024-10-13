package com.jubutech.grocery_2402

import androidx.lifecycle.LiveData

class GroceryRepository(private val groceryDao: GroceryDao) {

    val allGroceries: LiveData<List<GroceryItem>> = groceryDao.getAllGroceries()

    suspend fun insert(item: GroceryItem) {
        groceryDao.insertGrocery(item)
    }

    suspend fun delete(item: GroceryItem) {
        groceryDao.deleteGrocery(item)
    }
}
