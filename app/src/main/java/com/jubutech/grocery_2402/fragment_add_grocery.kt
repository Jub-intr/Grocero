package com.jubutech.grocery_2402

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jubutech.grocery_2402.databinding.FragmentAddGroceryBinding


class fragment_add_grocery : Fragment() {



    private lateinit var binding: FragmentAddGroceryBinding
    private lateinit var viewModel: GroceryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGroceryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val price = binding.etPrice.text.toString().toDoubleOrNull()
            val quantity = binding.etQuantity.text.toString().toIntOrNull()

            if (name.isNotEmpty() && price != null && quantity != null) {
                val grocery = GroceryItem(name = name, price = price, quantity = quantity)
                viewModel.insert(grocery)
                findNavController().navigate(R.id.action_fragment_add_grocery_to_fragment_grocery_list)
            }
        }
        return binding.root
    }
}