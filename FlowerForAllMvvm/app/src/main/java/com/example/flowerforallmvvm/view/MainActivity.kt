package com.example.flowerforallmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowerforallmvvm.R
import com.example.flowerforallmvvm.adapter.FlowerClick
import com.example.flowerforallmvvm.adapter.FlowersAdapter
import com.example.flowerforallmvvm.databinding.ActivityMainBinding
import com.example.flowerforallmvvm.model.FlowerModel
import com.example.flowerforallmvvm.viewmodel.FlowerViewModel
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), FlowersListener {

    private var flowersAdapter: FlowersAdapter? = null

    private val viewModel by lazy {
        ViewModelProvider(this).get(FlowerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.flowersListener = this

        flowersAdapter = FlowersAdapter(FlowerClick {
            // defines what the click on the cake does
            viewModel.displayFlowerToast(it)
        })

        binding.root.recyclerview_list.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = flowersAdapter
        }

        viewModel.flowersList.observe(this
        ) { flowersList ->
            flowersList?.apply {
                flowersAdapter?.flowersList = flowersList
            }
        }
    }

    override fun onItemClick(cakeModel: FlowerModel) {
        // this is associated with a single item clicked
        Toast.makeText(this, cakeModel.name, Toast.LENGTH_LONG).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}