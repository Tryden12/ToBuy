package com.tryden.tobuy.ui.add

import android.content.res.ColorStateList
import android.graphics.Typeface
import com.airbnb.epoxy.EpoxyController
import com.tryden.tobuy.R
import com.google.android.material.R.attr.*
import com.tryden.tobuy.arch.ToBuyViewModel
import com.tryden.tobuy.databinding.ModelCategoryItemSelectionBinding
import com.tryden.tobuy.getAttrColor
import com.tryden.tobuy.ui.epoxy.LoadingEpoxyModel
import com.tryden.tobuy.ui.epoxy.ViewBindingKotlinModel

class CategoryViewStateEpoxyController(
    private val onCategorySelected: (String) -> Unit
) : EpoxyController() {

    var viewState = ToBuyViewModel.CategoriesViewState()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        if (viewState.isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        viewState.itemList.forEach { item ->
            CategoryViewStateItem(item, onCategorySelected).id(item.categoryEntity.id).addTo(this)
        }
    }

    data class CategoryViewStateItem(
        val item: ToBuyViewModel.CategoriesViewState.Item,
        private val onCategorySelected: (String) -> Unit
    ): ViewBindingKotlinModel<ModelCategoryItemSelectionBinding>(R.layout.model_category_item_selection) {

        override fun ModelCategoryItemSelectionBinding.bind() {
            textView.text = item.categoryEntity.name
            root.setOnClickListener { onCategorySelected(item.categoryEntity.id) }

            val colorRes = if (item.isSelected) colorSecondary else colorOnSurface
            val color = root.getAttrColor(colorRes)
            textView.setTextColor(color)
            textView.typeface = if (item.isSelected) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
            root.setStrokeColor(ColorStateList.valueOf(color))
        }
    }
}