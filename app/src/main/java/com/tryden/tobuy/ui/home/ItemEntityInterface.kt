package com.tryden.tobuy.ui.home

import com.tryden.tobuy.database.entity.ItemEntity

interface ItemEntityInterface {

    fun onBumpPriority(itemEntity: ItemEntity)
    fun onItemSelected(itemEntity: ItemEntity)
}