package com.rivzdev.githubuserapp.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivzdev.githubuserapp.databinding.FragmentFollowingBinding
import com.rivzdev.githubuserapp.model.data.Users
import com.rivzdev.githubuserapp.view.adapter.FollowAdapter
import com.rivzdev.githubuserapp.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FollowAdapter
    private lateinit var viewModel: FollowingViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowAdapter()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)

        val user = activity?.intent?.getParcelableExtra<Users>(EXTRA_USER) as Users

        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(activity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter
        }

        user.login?.let { viewModel.setFollowing(it)
            showLoading(true)}

        viewModel.getFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
            }
        })

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}