package com.example.avc.database.dao

import androidx.room.*
import com.example.avc.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {
    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insert(productEntity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("DELETE FROM product_table")
    fun deleteAllProducts()
}
