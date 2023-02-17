package com.tryden.tobuy

import com.airbnb.epoxy.EpoxyController
import com.tryden.tobuy.ui.epoxy.models.HeaderEpoxyModel

fun EpoxyController.addHeaderModel(headerText: String) {
    HeaderEpoxyModel(headerText).id(headerText).addTo(this)
}