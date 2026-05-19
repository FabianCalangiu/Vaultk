package com.unibo.android.uicompose.extensions

import androidx.annotation.StringRes

import com.unibo.android.domain.models.AccommodationType
import com.unibo.android.uicompose.R


@StringRes fun AccommodationType.toResId(): Int {
    return when (this) {
        is AccommodationType.Hotel -> R.string.hotel
        is AccommodationType.Apartment -> R.string.apartment
    }
}