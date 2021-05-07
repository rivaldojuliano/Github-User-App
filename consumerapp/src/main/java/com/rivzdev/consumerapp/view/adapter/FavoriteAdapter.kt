package com.rivzdev.consumerapp.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivzdev.consumerapp.databinding.ItemRowFavoriteBinding
import com.rivzdev.consumerapp.model.data.Users
import com.rivzdev.consumerapp.view.ui.activity.DetailUserActivity

class FavoriteAdapter(private val activity: Activity): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    var listFavorite = ArrayList<Users>()

    set(listFavorite) {
        if (listFavorite.size >= 0) {
            this.listFavorite.clear()
        }
        this.listFavorite.addAll(listFavorite)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding: ItemRowFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(users.name)
                    .apply(RequestOptions().override(100, 100))
                    .into(circleImage)

                tvUsername.text = users.login
                cvItemFavorite.setOnClickListener {
                    val mIntent = Intent(activity, DetailUserActivity::class.java)
                    mIntent.putExtra(DetailUserActivity.EXTRA_USER, users)
                    activity.startActivity(mIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemRowFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = this.listFavorite.size
}