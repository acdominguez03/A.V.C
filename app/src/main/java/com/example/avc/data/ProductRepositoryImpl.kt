package com.example.avc.data

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.avc.database.dao.ProductDAO
import com.example.avc.database.entity.ProductEntity
import com.example.avc.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ProductRepositoryImpl(
    private val productDAO: ProductDAO
) : ProductRepository {
    override fun getAllProducts(): Flow<List<ProductEntity>> = flow {
        try {
            emitAll(productDAO.getAllProducts())
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al obtener los productos de la BBDD")
        }
    }

    override suspend fun deleteAllProducts() {
        try {
            productDAO.deleteAllProducts()
            Log.d("MY_TAG", "Productos borrados")
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al borrar los productos de la BBDD")
        }
    }

    override suspend fun insertProduct(productEntity: ProductEntity) {
        try {
            productDAO.insert(productEntity)
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al añadir el producto a la BBDD")
        }
    }

    override suspend fun insertAll(products: List<ProductEntity>) {
        try {
            productDAO.insertAll(products)
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al añadir los productos a la BBDD")
        }
    }

    override suspend fun updateProducts(products: List<ProductEntity>) {
        try {
            productDAO.updateProducts(products)
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al actualizar los productos de la BBDD")
        }
    }
}
