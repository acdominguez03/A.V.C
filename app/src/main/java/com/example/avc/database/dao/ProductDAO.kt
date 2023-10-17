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

    @Query("UPDATE product_table SET amount = :amount WHERE id = :id")
    suspend fun update(amount: Int, id: Long)

    @Transaction
    suspend fun updateProducts(list: List<ProductEntity>) {
        list.forEach {
            update(it.amount, it.id)
        }
    }

    @Query(
        "SELECT COUNT(delivery_table.user_id) - (SELECT SUM(ticket_table.price) FROM ticket_table) " +
            "FROM delivery_table " +
            "WHERE delivery_table.status = 'PAID' OR delivery_table.status = 'INVITATION'"
    )
    fun getProfits(): Flow<Double>
}
