package com.test.tmdb.ui.main.adapter


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.tmdb.R
import com.test.tmdb.ui.main.pojo.Movies
import com.test.tmdb.ui.detail.MovieDetailActivity


/**
 * Created by admin on 01/07/19.
 */
class InnerMainAdapter(private val mContext: Context, private val movieList: List<Movies?>?) : RecyclerView.Adapter<InnerMainAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(p0.context)
                .inflate(R.layout.row_inner_recycler_view_adapter, p0, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // holder.tvGenreName!!.text = movieList!![position].posterPath
        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w185/${movieList!![position]!!.posterPath}")
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(holder.imgMovie!!)


        holder.imgMovie!!.setOnClickListener {
            val intent = Intent(mContext, MovieDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("movie_id", "${movieList[position]!!.id}")
            intent.putExtras(bundle)
            mContext.startActivity(intent)
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgMovie: ImageView? = null

        init {

            imgMovie = view.findViewById(R.id.imgMovie)
            //view.setOnClickListener(this)
        }


    }
}
