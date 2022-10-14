package com.uc.week4_retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.week4_retrofit.adapter.GenreAdapter
import com.uc.week4_retrofit.adapter.ProductionCompanyAdapter
import com.uc.week4_retrofit.adapter.SpokenLanguageAdapter
import com.uc.week4_retrofit.databinding.ActivityMovieDetailBinding
import com.uc.week4_retrofit.helper.Const
import com.uc.week4_retrofit.model.Genre
import com.uc.week4_retrofit.model.ProductionCompany
import com.uc.week4_retrofit.model.SpokenLanguage
import com.uc.week4_retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var bain: ActivityMovieDetailBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterG: GenreAdapter
    private lateinit var adapterPC: ProductionCompanyAdapter
    private lateinit var adapterSL: SpokenLanguageAdapter

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

            if (response != null){
                bain.progressBar.visibility = View.INVISIBLE
            }

            bain.rvGenreMoviePoster.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterG = GenreAdapter(response.genres as List<Genre>)
            bain.rvGenreMoviePoster.adapter = adapterG

            bain.rvProductionCompanyMoviePoster.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterPC = ProductionCompanyAdapter(response.production_companies as List<ProductionCompany>)
            bain.rvProductionCompanyMoviePoster.adapter = adapterPC
//            Glide.with(applicationContext)
//                .load(Const.IMG_URL + response.production_companies as List<ProductionCompany>)
//                .into(adapterPC.onBindViewHolder())

            bain.rvSpokenLanguageMoviePoster.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterSL = SpokenLanguageAdapter(response.spoken_languages as List<SpokenLanguage>)
            bain.rvSpokenLanguageMoviePoster.adapter = adapterSL

            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(bain.imgPosterMovieDetail)

            bain.overviewMoviePoster.text = response.overview

        })

//        viewModel.getMovieGenre(Const.API_KEY, movieId)
//        viewModel.movieGenre.observe(this,{
//            response ->
//            bain.rvGenreMoviePoster.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//            adapterG = GenreAdapter(response)
//            bain.rvGenreMoviePoster.adapter = adapterG
//        })
    }
}