package com.example.avc.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.model.ExpensesItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query(
        "Select product_table.id AS productId, product_table.name AS productName, product_table.price AS productPrice, COUNT(delivery_table.product_id) AS quantity " + "FROM product_table " +
            "INNER JOIN delivery_table ON product_table.id = delivery_table.product_id " +
            "WHERE delivery_table.`status` = 'NO_PAID' AND delivery_table.user_id = :userId " +
            "GROUP BY product_table.name"
    )
    fun getUserExpenses(userId: Long): Flow<List<ExpensesItemModel>>

    @Query(
        "Select user_table.* " +
            "FROM user_table " +
            "INNER JOIN delivery_table ON user_table.id = delivery_table.user_id " +
            "WHERE delivery_table.`status` = 'NO_PAID' " +
            "GROUP BY delivery_table.user_id " + "HAVING COUNT(delivery_table.user_id) > 0 "
    )
    fun getUsersWithExpenses(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(users: List<UserEntity>)
}
