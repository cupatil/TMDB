package com.test.tmdb.ui.main.adapter


import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.test.tmdb.R
import com.test.tmdb.networking.ApiClient
import com.test.tmdb.networking.NetworkService
import com.test.tmdb.ui.main.pojo.GenresItem
import com.test.tmdb.ui.main.pojo.Movies
import com.test.tmdb.ui.main.pojo.MoviesDataList
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by admin on 01/07/19.
 */
class MainAdapter(private val mContext: Context, private val genreList: ArrayList<GenresItem>?) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return genreList?.size ?: 0
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(p0.context)
                .inflate(R.layout.row_main, p0, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvGenreName!!.text = genreList!![position].name

        val innerMainAdapter = InnerMainAdapter(mContext, genreList[position].movies)
        holder.innerRecyclerView!!.adapter = innerMainAdapter
        holder.innerRecyclerView!!.layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
        //innerMainAdapter.notifyDataSetChanged()
        if (genreList[position].movies == null || genreList[position].movies!!.isEmpty()) {
            holder.progressBar!!.visibility = View.VISIBLE
            holder.innerRecyclerView!!.visibility = View.GONE
            getMovieList(position, genreList[position].id)
        } else {
            holder.progressBar!!.visibility = View.GONE
            holder.innerRecyclerView!!.visibility = View.VISIBLE
        }

    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvGenreName: TextView? = null
        var progressBar: ProgressBar? = null
        var innerRecyclerView: RecyclerView? = null

        init {
            tvGenreName = view.findViewById(R.id.tvGenreName)
            innerRecyclerView = view.findViewById(R.id.innerRecyclerView)
            progressBar = view.findViewById(R.id.progressBar)
        }


    }

    fun updateChild(pos: Int, movieList: List<Movies>?) {
        val genre: GenresItem? = genreList!![pos]
        genre!!.movies = movieList
        genreList[pos] = genre
        notifyItemChanged(pos)
    }

    private fun getMovieList(pos: Int, genreId: Int?) {
        val apiService = ApiClient.client.create(NetworkService::class.java)

        val call = apiService.getGenreMovies(genreId)

        call.enqueue(object : Callback<MoviesDataList> {
            override fun onResponse(call: Call<MoviesDataList>, response: Response<MoviesDataList>) {
                try {
                    if (response.body() != null) {
                        updateChild(pos, response.body()!!.results)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<MoviesDataList>, t: Throwable) {

            }
        })
    }
}
