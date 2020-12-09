package com.extcode.project.movieappjetpacksubmission2.ui.tvshows

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.extcode.project.movieappjetpacksubmission2.R
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.TvShowEntity
import com.extcode.project.movieappjetpacksubmission2.databinding.ItemCardListBinding
import com.extcode.project.movieappjetpacksubmission2.ui.detail.DetailActivity
import com.extcode.project.movieappjetpacksubmission2.ui.detail.DetailType
import java.util.*

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    private var listTvShows = ArrayList<TvShowEntity>()

    fun setTvShows(tvShows: List<TvShowEntity>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemCardListBinding =
            ItemCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemCardListBinding)
    }

    override fun onBindViewHolder(holderTvShows: TvShowsViewHolder, position: Int) {
        holderTvShows.bind(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class TvShowsViewHolder(private val binding: ItemCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(binding) {
                title.text = tvShowEntity.name
                date.text = itemView.context.getString(
                    R.string.popularity_d,
                    tvShowEntity.popularity.toString()
                )
                popularity.text = tvShowEntity.originalLanguage

                userScore.text = tvShowEntity.voteAverage.toString()

                Glide.with(itemView.context)
                    .load(
                        itemView.context.getString(
                            R.string.baseUrlImage,
                            tvShowEntity.posterPath
                        )
                    )
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, DetailType.TV_SHOW.ordinal)
                        putExtra(DetailActivity.EXTRA_ID, tvShowEntity.id)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}