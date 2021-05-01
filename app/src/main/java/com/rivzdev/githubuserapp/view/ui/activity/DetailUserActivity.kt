package com.rivzdev.githubuserapp.view.ui.activity

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.rivzdev.githubuserapp.R
import com.rivzdev.githubuserapp.databinding.ActivityDetailUserBinding
import com.rivzdev.githubuserapp.model.data.Users
import com.rivzdev.githubuserapp.model.database.DatabaseContract
import com.rivzdev.githubuserapp.model.database.FavoriteHelper
import com.rivzdev.githubuserapp.view.adapter.SectionPagerAdapter
import com.rivzdev.githubuserapp.viewmodel.UserDetailViewModel

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: UserDetailViewModel

    private var statusFavorite = false
    private lateinit var favoriteHelper: FavoriteHelper

    companion object {
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text1,
            R.string.tab_text2
        )
        private val TEXT = intArrayOf(
            R.string.text_add,
            R.string.text_remove
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteHelper = FavoriteHelper.getInstance(applicationContext)
        favoriteHelper.open()

        val user = intent.getParcelableExtra<Users>(EXTRA_USER) as Users

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserDetailViewModel::class.java)

        user.login?.let { viewModel.setDetailUser(it) }
        viewModel.getDetailUser().observe(this, {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .apply(RequestOptions().override(150, 150))
                        .into(circleImage)

                    tvUsername.text = it.login
                    tvName.text = it.name
                    tvLocation.text = it.location
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, viewPager) {tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()

            supportActionBar?.elevation = 0f
        }

        val values = ContentValues()
        values.put(DatabaseContract.FavoriteColumns.LOGIN, user.login)
        values.put(DatabaseContract.FavoriteColumns.AVATAR_URL, user.avatar_url)

        binding.floatingButton.setOnClickListener {
            if (!statusFavorite) {
                statusFavorite = !statusFavorite
                favoriteHelper.insert(values)
                setStatusFavorite(statusFavorite)
                Toast.makeText(this, TEXT[0], Toast.LENGTH_SHORT).show()
            } else {
                statusFavorite = !statusFavorite
                favoriteHelper.deleteById(user.login.toString())
                setStatusFavorite(statusFavorite)
                Toast.makeText(this, TEXT[1], Toast.LENGTH_SHORT).show()
            }
        }

        val cursor: Cursor = favoriteHelper.queryById(user.login.toString())
        if (cursor.moveToNext()) {
            statusFavorite = true
            setStatusFavorite(statusFavorite)
        }
    }


    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.floatingButton.setImageResource(R.drawable.ic_heart_follow)
        } else {
            binding.floatingButton.setImageResource(R.drawable.ic_heart_un_follow)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
            R.id.menu_favorite -> {
                val mIntent = Intent(this@DetailUserActivity, FavoriteActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}