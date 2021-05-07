package com.rivzdev.consumerapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivzdev.consumerapp.databinding.ItemRowUsersBinding
import com.rivzdev.consumerapp.model.data.Users

class FollowAdapter: RecyclerView.Adapter <FollowAdapter.FollowViewHolder>() {
    private val listUser = ArrayList<Users>()

    fun setData(items: ArrayList<Users>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    inner class FollowViewHolder(private val binding: ItemRowUsersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Users) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(user.avatar_url)
                        .apply(RequestOptions().override(100, 100))
                        .into(circleImage)

                tvUsername.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}