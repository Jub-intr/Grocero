package com.jubutech.grocery_2402

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jubutech.grocery_2402.databinding.FragmentGroceryListBinding

class fragment_grocery_list : Fragment() {



    private lateinit var binding: FragmentGroceryListBinding
    private lateinit var viewModel: GroceryViewModel
    private lateinit var adapter: GroceryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroceryListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)

        adapter = GroceryAdapter { grocery ->
            viewModel.delete(grocery)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.allGroceries.observe(viewLifecycleOwner, { groceries ->
            adapter.submitList(groceries)
        })

        return binding.root
    }
}