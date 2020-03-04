package com.example.modular.movie_feature.moviedetail.viewcustom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.modular.movie_feature.R
import kotlinx.android.synthetic.main.component_movie_detail.view.*


class MovieDetailComponent : ConstraintLayout {

    init {
        LayoutInflater.from(context).inflate(R.layout.component_movie_detail, this, true)
        rootView.setBackgroundColor(ContextCompat.getColor(context, R.color.md_grey_900))
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    fun setMovieDetail(data: MovieData) {
        tv_title.text = data.tile
        tv_description.text = data.description
        img_poster.setImageDrawable(ContextCompat.getDrawable(context, data.poster))
        img_thumbnail.setImageDrawable(ContextCompat.getDrawable(context, data.thumbnail))
    }

}

data class MovieData(
    val tile: String,
    val description: String,
    val thumbnail: Int,
    val poster: Int
)