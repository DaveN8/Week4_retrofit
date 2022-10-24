package com.uc.week4_retrofit.view

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.uc.week4_retrofit.helper.Const
import com.uc.week4_retrofit.view.ui.theme.Week4_retrofitTheme
import com.uc.week4_retrofit.viewmodel.MoviesViewModel
import com.uc.week4_retrofit.model.Result
import com.uc.week4_retrofit.view.widgets.MovieCard


class ComposeNowPlaying : ComponentActivity() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get data dr api
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        viewModel.getNowPlaying(Const.API_KEY, "en-US", 1)
        viewModel.nowplaying.observe(this, {
            response->
            //menampilkan data di layar
            setContent {
                Week4_retrofitTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MovieList(movieList = response)
                    }
                }
            }
        })


    }
}

@Composable
fun MovieList(movieList: ArrayList<Result>) {
    LazyColumn{
        itemsIndexed(items = movieList){index, item ->
            MovieCard(movie = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Week4_retrofitTheme {

    }
}