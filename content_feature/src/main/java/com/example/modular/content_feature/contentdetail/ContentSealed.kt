package com.example.modular.content_feature.contentdetail

sealed class ContentSealed

data class ContentDetails(
    val title: String,
    val body: String,
    val description: String,
    val isFavorite: Boolean
) : ContentSealed()

object NoContent : ContentSealed()