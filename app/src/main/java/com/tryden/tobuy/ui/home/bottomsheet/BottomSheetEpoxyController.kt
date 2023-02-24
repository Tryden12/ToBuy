package com.tryden.tobuy.ui.home.bottomsheet

import com.airbnb.epoxy.EpoxyController
import com.tryden.tobuy.R
import com.tryden.tobuy.arch.ToBuyViewModel
import com.tryden.tobuy.databinding.ModelSortOrderItemBinding
import com.tryden.tobuy.ui.epoxy.ViewBindingKotlinModel

class BottomSheetEpoxyController(
    private val sortOptions: Array<ToBuyViewModel.HomeViewState.Sort>,
    private val selectedCallback: (ToBuyViewModel.HomeViewState.Sort) -> Unit
) : EpoxyController() {

    override fun buildModels() {
        sortOptions.forEach {
            SortOrderItemEpoxyModel(it, selectedCallback).id(it.displayName).addTo(this)
        }
    }

    data class SortOrderItemEpoxyModel(
        val sort: ToBuyViewModel.HomeViewState.Sort,
        val selectedCallback: (ToBuyViewModel.HomeViewState.Sort) -> Unit
    ): ViewBindingKotlinModel<ModelSortOrderItemBinding>(R.layout.model_sort_order_item) {
        override fun ModelSortOrderItemBinding.bind() {
            textView.text = sort.displayName
            root.setOnClickListener { selectedCallback(sort) }
        }
    }
}