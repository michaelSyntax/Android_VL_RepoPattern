package com.example.summerslurp_repopattern.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.summerslurp_repopattern.R
import com.example.summerslurp_repopattern.databinding.ListItemBinding
import com.example.summerslurp_repopattern.model.datamodels.Drink

class DrinkAdapter(
    private val listOfDrinks: List<Drink>
): RecyclerView.Adapter<DrinkAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfDrinks.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val drink = listOfDrinks[position]

        holder.binding.listText.text = drink.name

        holder.binding.listImage.load(drink.picture) {
            error(R.drawable.ic_round_broken_image_24)
            transformations(RoundedCornersTransformation(10f))
        }
    }
}