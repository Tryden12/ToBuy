package com.tryden.tobuy

import android.view.View
import androidx.annotation.ColorInt
import com.airbnb.epoxy.EpoxyController
import com.google.android.material.color.MaterialColors
import com.tryden.tobuy.ui.epoxy.models.HeaderEpoxyModel

fun EpoxyController.addHeaderModel(headerText: String) {
    HeaderEpoxyModel(headerText).id(headerText).addTo(this)
}

@ColorInt
fun View.getAttrColor(attrResId: Int) : Int{
    return MaterialColors.getColor(this, attrResId)
}