package com.tryden.tobuy.ui.home

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyController
import com.tryden.tobuy.R
import com.tryden.tobuy.addHeaderModel
import com.tryden.tobuy.arch.ToBuyViewModel
import com.tryden.tobuy.database.entity.ItemEntity
import com.tryden.tobuy.database.entity.ItemWIthCategoryEntity
import com.tryden.tobuy.databinding.ModelEmptyStateBinding
import com.tryden.tobuy.databinding.ModelHeaderItemBinding
import com.tryden.tobuy.databinding.ModelItemEntityBinding
import com.tryden.tobuy.ui.epoxy.LoadingEpoxyModel
import com.tryden.tobuy.ui.epoxy.ViewBindingKotlinModel
import com.tryden.tobuy.ui.epoxy.models.HeaderEpoxyModel

class HomeEpoxyController(
    private val itemEntityInterface: ItemEntityInterface
): EpoxyController() {

   var viewState: ToBuyViewModel.HomeViewState = ToBuyViewModel.HomeViewState(isLoading = true)
        set(value) {
            field = value
            requestModelBuild()
        }


    override fun buildModels() {
        if (viewState.isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (viewState.dataList.isEmpty()) {
            EmptyStateEpoxyModel().id("empty-state").addTo(this)
            return
        }


        viewState.dataList.forEach { dataItem ->
            if (dataItem.isHeader) {
                addHeaderModel(dataItem.data as String)
                return@forEach
            }

            val itemWithCategoryEntity = dataItem.data as ItemWIthCategoryEntity
            ItemEntityEpoxyModel(itemWithCategoryEntity, itemEntityInterface)
                .id(itemWithCategoryEntity.itemEntity.id)
                .addTo(this)
        }
    }

    data class ItemEntityEpoxyModel(
        val itemEntity: ItemWIthCategoryEntity,
        val itemEntityInterface: ItemEntityInterface
    ): ViewBindingKotlinModel<ModelItemEntityBinding>(R.layout.model_item_entity) {

        override fun ModelItemEntityBinding.bind() {
            titleTextView.text = itemEntity.itemEntity.title

            if (itemEntity.itemEntity.description == null || itemEntity.itemEntity.description.isEmpty()) {
                descriptionTextView.isGone = true
            } else {
                descriptionTextView.isVisible = true
                descriptionTextView.text = itemEntity.itemEntity.description
            }

            priorityTextView.setOnClickListener {
                itemEntityInterface.onBumpPriority(itemEntity.itemEntity)
            }


            val colorRes = when (itemEntity.itemEntity.priority) {
                1 -> android.R.color.holo_green_dark
                2 -> android.R.color.holo_orange_dark
                3 -> android.R.color.holo_red_dark
                else -> R.color.color_accent
            }

            val color = ContextCompat.getColor(root.context, colorRes)
            priorityTextView.setBackgroundColor(color)
            root.setStrokeColor(ColorStateList.valueOf(color))
            root.setOnClickListener {
                itemEntityInterface.onItemSelected(itemEntity.itemEntity)
            }
            categoryNameTextView.text = itemEntity.categoryEntity?.name
        }
    }

    class EmptyStateEpoxyModel:ViewBindingKotlinModel<ModelEmptyStateBinding>
        (R.layout.model_empty_state) {
        override fun ModelEmptyStateBinding.bind() {
            // nothing to do
        }
    }

}