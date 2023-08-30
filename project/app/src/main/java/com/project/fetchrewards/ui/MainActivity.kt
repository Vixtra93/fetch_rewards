package com.project.fetchrewards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.fetchrewards.R
import com.project.fetchrewards.databinding.ActivityMainBinding
import com.project.fetchrewards.remote.models.Item
import com.project.fetchrewards.viewmodels.MainViewModel
import com.project.fetchrewards.ui.adapters.GroupAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var groupAdapter: GroupAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initRecyclerView()
        with(viewModel) {
            this.items.observe(this@MainActivity, ::setupItems)
            this.isLoading.observe(this@MainActivity, ::isProgressLoading)
            this.errorMessage.observe(this@MainActivity, ::errorMessage)
        }
    }

    private fun setupItems(map: Map<Int, List<Item>>) {
        groupAdapter.setItems(map)
    }

    private fun isProgressLoading(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun errorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
    private fun initRecyclerView() {
        groupAdapter = GroupAdapter()
        binding.rcvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

}