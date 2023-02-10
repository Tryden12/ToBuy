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

    val transactionCompleteLiveData = MutableLiveData<Boolean>()

    fun init(appDatabase: AppDatabase) {
        repository = ToBuyRepository(appDatabase)

        viewModelScope.launch {
            val items = repository.getAllItems().collect { items ->
                itemEntitiesLiveData.postValue(items)
            }
        }
    }

    fun insertItem(itemEntity: ItemEntity) {
        viewModelScope.launch {
            repository.insertItem(itemEntity)

            transactionCompleteLiveData.postValue(true)
        }
    }

    fun deleteItem(itemEntity: ItemEntity) {
        viewModelScope.launch {
            repository.deleteItem(itemEntity)
        }
    }

}