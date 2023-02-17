package com.tryden.tobuy.ui.epoxy.models

import com.tryden.tobuy.R
import com.tryden.tobuy.databinding.ModelHeaderItemBinding
import com.tryden.tobuy.ui.epoxy.ViewBindingKotlinModel

data class HeaderEpoxyModel(
    val headerText: String
): ViewBindingKotlinModel<ModelHeaderItemBinding>(R.layout.model_header_item) {

    override fun ModelHeaderItemBinding.bind() {
        textView.text = headerText
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}