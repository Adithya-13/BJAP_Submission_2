package com.extcode.project.movieappjetpacksubmission2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.MovieEntity
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.TvShowEntity
import com.extcode.project.movieappjetpacksubmission2.data.source.remote.RemoteDataSource
import com.extcode.project.movieappjetpacksubmission2.data.source.remote.response.Movie
import com.extcode.project.movieappjetpacksubmission2.data.source.remote.response.TvShow

class MovieAppRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieAppDataSource {

    companion object {
        @Volatile
        private var instance: MovieAppRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieAppRepository =
            instance ?: synchronized(this) {
                instance ?: MovieAppRepository(remoteData)
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()
                if (movieResponses != null) {
                    for (movie in movieResponses) {
                        with(movie) {
                            movieList.add(
                                MovieEntity(
                                    overview,
                                    originalLanguage,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    title,
                                    voteCount,
                                    posterPath
                                )
                            )
                        }
                    }
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (tvShowResponses != null) {
                    for (tvShow in tvShowResponses) {
                        with(tvShow) {
                            tvShowList.add(
                                TvShowEntity(
                                    firstAirDate,
                                    overview,
                                    originalLanguage,
                                    popularity,
                                    voteAverage,
                                    name,
                                    id,
                                    voteCount,
                                    posterPath,
                                )
                            )
                        }
                    }
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getMovieById(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>?) {
                lateinit var movie: MovieEntity
                if (movieResponses != null) {
                    for (movieItem in movieResponses) {
                        if (movieId == movieItem.id.toString())
                            with(movieItem) {
                                movie = MovieEntity(
                                    overview,
                                    originalLanguage,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    title,
                                    voteCount,
                                    posterPath
                                )
                            }
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    override fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?) {
                lateinit var tvShow: TvShowEntity
                if (tvShowResponses != null) {
                    for (tvShowItem in tvShowResponses) {
                        if (tvShowId == tvShowItem.id.toString())
                            with(tvShowItem) {
                                tvShow = TvShowEntity(
                                    firstAirDate,
                                    overview,
                                    originalLanguage,
                                    popularity,
                                    voteAverage,
                                    name,
                                    id,
                                    voteCount,
                                    posterPath,
                                )
                            }
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })
        return tvShowResult
    }
}