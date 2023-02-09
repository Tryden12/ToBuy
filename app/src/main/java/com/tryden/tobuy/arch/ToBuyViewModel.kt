package com.tryden.tobuy.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.tobuy.database.AppDatabase
import com.tryden.tobuy.database.entity.ItemEntity
import kotlinx.coroutines.launch

class ToBuyViewModel() : ViewModel() {

    private lateinit var repository: ToBuyRepository

    val itemEntitiesLiveData = MutableLiveData<List<ItemEntity>>()

    fun init(appDatabase: AppDatabase) {
        repository = ToBuyRepository(appDatabase)

        viewModelScope.launch {
            val items = repository.getAllItems()
            itemEntitiesLiveData.postValue(items)
        }
    }

    suspend fun insertItem(itemEntity: ItemEntity) {
        repository.insertItem(itemEntity)
    }

    suspend fun deleteItem(itemEntity: ItemEntity) {
        repository.deleteItem(itemEntity)
    }

}