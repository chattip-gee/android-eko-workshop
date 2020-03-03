package com.example.modular.ekoworkshop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import arrow.core.*
import kotlinx.android.synthetic.main.example_complex_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_complex_layout)

        val result1: Either<Exception, Int> = Right(24)
        val result2: Either<Exception, Int> = Left(Exception())
        Log.d("ComeHere ",result1.toString())


        //Example used sealed class
        when (val content = getContentDetails(hasContent = true)) {
            is ContentDetails -> {
                tv_title.text = content.title
                tv_body.text = content.body
                tv_description.text = content.description
                img_ic_favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite_24px
                    )
                )
            }
            NoContent -> {
                val noContent = "No Content"
                tv_title.text = noContent
                tv_body.text = noContent
                tv_description.text = noContent
                img_ic_favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite_border_24px
                    )
                )
            }
        }

    }

    private fun getContentDetails(hasContent: Boolean): ContentSealed {
        return if (hasContent) {
            ContentDetails(
                title = "Our Solar-System’s Milky Way Orbit –“A Danger-Fraught 226-Million-Year Three-Dimensional Ballet”",
                body = "Our Solar System’s orbit through the Milky Way has only happened 20.4 times since the Sun itself formed 4.6 billion years ago.",
                description = "It’s estimated that the Sun will continue fusing hydrogen for another 7 billion years. In other words, it only has another 31 orbits it can make before it runs out of fuel. Researchers at Cardiff University suggested that there is a “genocidal countdown” built into the motion of our solar system –that our system’s orbit through the Milky Way encounters regular speedbumps – and by “speedbumps”, meaning “potentially extinction-causing asteroids.”",
                isFavorite = true
            )
        } else NoContent
    }
}
