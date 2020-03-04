package com.example.modular.movie_feature.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modular.movie_feature.R
import com.example.modular.movie_feature.moviedetail.viewcustom.MovieData
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movie_detail_component.setMovieDetail(
            MovieData(
                tile = "Parasite",
                description = "The Kim family—father Ki-taek, mother Chung-sook, daughter Ki-jung and son Ki-woo—live in a small semi-basement apartment (banjiha),[10] have low-paying temporary jobs as pizza box folders, and struggle to make ends meet.[11] One day, Min-hyuk, a friend of Ki-woo's and a university student, gives the family a scholar's rock, which is meant to promise wealth to whoever possesses it. He suggests that, when he leaves to study abroad, Ki-woo should take over his job as an English tutor to the wealthy Park family's daughter, Da-hye. Ki-woo poses as a university student and is hired by the Parks; Mrs. Park decides they will call him \"Kevin\".",
                poster = R.drawable.parasite_poster,
                thumbnail = R.drawable.parasite_thumbnail
            )
        )
    }
}
