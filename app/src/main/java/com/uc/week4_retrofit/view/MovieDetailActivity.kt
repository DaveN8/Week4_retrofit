package com.uc.week4_retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.uc.week4_retrofit.databinding.ActivityMovieDetailBinding
import com.uc.week4_retrofit.helper.Const
import com.uc.week4_retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var bain: ActivityMovieDetailBinding
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bain = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(bain.root)

        val movieId = intent.getIntExtra("movie.id", 0)
        Toast.makeText(applicationContext, "Movie ID: ${movieId}", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovieDetails(Const.API_KEY, movieId)
        viewModel.movieDetails.observe(this, { response ->
            bain.tvTitleMovieDetail.apply {
                text = response.title
            }
            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(bain.imgPosterMovieDetail)
        })
    }
}