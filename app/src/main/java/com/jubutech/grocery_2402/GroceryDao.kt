package com.jubutech.grocery_2402

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GroceryDao {

    @Insert
     fun insertGrocery(item: GroceryItem)

    @Query("SELECT * FROM grocery_table")
    fun getAllGroceries(): LiveData<List<GroceryItem>>

    @Delete
     fun deleteGrocery(item: GroceryItem)
}
