package com.tryden.tobuy.ui.home

import com.tryden.tobuy.database.entity.ItemEntity

interface ItemEntityInterface {

    fun onDeleteItemEntity(itemEntity: ItemEntity)
    fun onBumpPriority(itemEntity: ItemEntity)

}