package com.example.modular.ekoworkshop

sealed class ContentSealed

data class ContentDetails(
    val title: String,
    val body: String,
    val description: String,
    val isFavorite: Boolean
) : ContentSealed()

object NoContent : ContentSealed()