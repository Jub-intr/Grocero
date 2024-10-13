package com.jubutech.grocery_2402

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jubutech.grocery_2402.databinding.ItemGroceryBinding

class GroceryAdapter(private val deleteGrocery: (GroceryItem) -> Unit) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    private var groceries: List<GroceryItem> = listOf()

    class GroceryViewHolder(val binding: ItemGroceryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val binding = ItemGroceryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroceryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val grocery = groceries[position]
        holder.binding.tvName.text = grocery.name
        holder.binding.tvPrice.text = "Price: ${grocery.price}"
        holder.binding.tvQuantity.text = "Quantity: ${grocery.quantity}"

        holder.binding.btnDelete.setOnClickListener {
            deleteGrocery(grocery)
        }
    }

    override fun getItemCount(): Int = groceries.size

    fun submitList(list: List<GroceryItem>) {
        groceries = list
        notifyDataSetChanged()
    }
}
