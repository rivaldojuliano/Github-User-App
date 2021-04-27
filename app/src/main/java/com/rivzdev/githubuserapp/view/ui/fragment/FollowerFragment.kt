package com.rivzdev.githubuserapp.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivzdev.githubuserapp.databinding.FragmentFollowerBinding
import com.rivzdev.githubuserapp.model.data.Users
import com.rivzdev.githubuserapp.view.adapter.FollowAdapter
import com.rivzdev.githubuserapp.viewmodel.FollowerViewModel

class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FollowAdapter
    private lateinit var viewModel: FollowerViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowAdapter()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel::class.java)

        val user = activity?.intent?.getParcelableExtra<Users>(EXTRA_USER) as Users

        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(activity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter
        }

        user.login?.let { viewModel.setFollower(it)
            showLoading(true)}

        viewModel.getFollower().observe(viewLifecycleOwner, {
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