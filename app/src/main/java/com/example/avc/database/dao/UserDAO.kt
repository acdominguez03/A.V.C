package com.example.avc.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.avc.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    /*@Query(
        "Select user_table.name, SUM(product_table.price * product_per_delivery_table.amount) AS moneyToPay, product_table.name FROM user_table " +
            "INNER JOIN delivery_table ON delivery_table.user_id = user_table.id " +
            "INNER JOIN product_per_delivery_table ON id_delivery = delivery_table.id " +
            "INNER JOIN product_table ON product_table.id = product_per_delivery_table.id_product " +
            "WHERE product_per_delivery_table.paid = 'No pagado' " +
            "GROUP BY user_table.name, product_table.name"
    )
    fun getUsers(): List<UserEntity>*/

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(users: List<UserEntity>)
}