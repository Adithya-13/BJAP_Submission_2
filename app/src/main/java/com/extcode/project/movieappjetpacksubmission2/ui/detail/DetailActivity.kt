package com.extcode.project.movieappjetpacksubmission2.ui.detail

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.extcode.project.movieappjetpacksubmission2.R
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.MovieEntity
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.TvShowEntity
import com.extcode.project.movieappjetpacksubmission2.databinding.ActivityDetailBinding
import com.extcode.project.movieappjetpacksubmission2.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extraType"
        const val EXTRA_ID = "extraId"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val enumType: DetailType = DetailType.values()[type]

        val id = intent.getIntExtra(EXTRA_ID, -1)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        activityDetailBinding.progressBar.visibility = VISIBLE
        activityDetailBinding.nestedScrollView.visibility = GONE
        when (enumType) {
            DetailType.MOVIE -> {
                viewModel.selectedMovieId(id.toString())
                viewModel.getMovieDetail().observe(this, { movie ->
                    activityDetailBinding.progressBar.visibility = GONE
                    activityDetailBinding.nestedScrollView.visibility = VISIBLE
                    populateMovieDetail(movie)
                })
            }
            DetailType.TV_SHOW -> {
                viewModel.selectedTvShowId(id.toString())
                viewModel.getTvShowDetail().observe(this, { tvShow ->
                    activityDetailBinding.progressBar.visibility = GONE
                    activityDetailBinding.nestedScrollView.visibility = VISIBLE
                    populateTvShowDetail(tvShow)
                })
            }
        }

        activityDetailBinding.backButton.setOnClickListener { finish() }
        activityDetailBinding.share.setOnClickListener { share() }
    }

    private fun populateMovieDetail(movieEntity: MovieEntity) {
        with(activityDetailBinding) {
            titleDetail.text = movieEntity.title
            date.text = movieEntity.releaseDate
            overview.text = movieEntity.overview
            popularity.text = getString(
                R.string.popularity_detail,
                movieEntity.popularity.toString(),
                movieEntity.voteCount.toString(),
                movieEntity.voteAverage.toString()
            )
            userScore.text = movieEntity.voteAverage.toString()
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, movieEntity.posterPath))
                .into(posterTopBar)
            posterTopBar.tag = movieEntity.posterPath

            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, movieEntity.posterPath))
                .into(subPoster)
            subPoster.tag = movieEntity.posterPath
        }
    }

    private fun populateTvShowDetail(tvShowEntity: TvShowEntity) {
        with(activityDetailBinding) {
            titleDetail.text = tvShowEntity.name
            date.text = tvShowEntity.firstAirDate
            overview.text = tvShowEntity.overview
            popularity.text = getString(
                R.string.popularity_detail,
                tvShowEntity.popularity.toString(),
                tvShowEntity.voteCount.toString(),
                tvShowEntity.voteAverage.toString()
            )
            userScore.text = tvShowEntity.voteAverage.toString()
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, tvShowEntity.posterPath))
                .into(posterTopBar)
            posterTopBar.tag = tvShowEntity.posterPath
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, tvShowEntity.posterPath))
                .into(subPoster)
            subPoster.tag = tvShowEntity.posterPath
        }
    }

    private fun share() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder.from(this).apply {
            setType(mimeType)
            setChooserTitle(getString(R.string.shareTitle))
            setText(getString(R.string.shareBody))
            startChooser()
        }
    }
}