package com.example.modular.ekoworkshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*//Workshop Functional Programming
        runExampleFP()*/

        installModule(modules) {
            if (it) {
                tv_error.visibility = View.GONE
                btn_open_content.visibility = View.VISIBLE
                btn_open_movie.visibility = View.VISIBLE
            } else tv_error.visibility = View.VISIBLE
        }

        btn_open_content.setOnClickListener {
            val intent = Intent().setClassName(
                this,
                "com.example.modular.content_feature.contentdetail.ContentDetailActivity"
            )
            startActivity(intent)
        }

        btn_open_movie.setOnClickListener {
            val intent = Intent().setClassName(
                this,
                "com.example.modular.movie_feature.moviedetail.MovieDetailActivity"
            )
            startActivity(intent)
        }

    }
}
