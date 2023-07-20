package com.coroutines_retrofit_mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coroutines_retrofit_mvvm.data.model.Movie
import com.coroutines_retrofit_mvvm.databinding.ItemImageBinding

class ListAdapter(private val movie: List<Movie>) : RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = movie[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = movie.size

    inner class UserViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.title.text = movie.name
            Glide.with(itemView.context).load(movie.imageUrl).into(binding.imageView)
        }
    }
}