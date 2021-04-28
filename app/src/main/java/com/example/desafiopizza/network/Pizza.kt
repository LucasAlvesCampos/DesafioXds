package com.example.desafiopizza.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pizza(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rating: Int,
    val priceP: Double,
    val priceM: Double,
    val priceG: Double) : Parcelable