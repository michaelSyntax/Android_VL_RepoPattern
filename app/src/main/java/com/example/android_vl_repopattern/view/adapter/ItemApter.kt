package com.example.android_vl_repopattern.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.android_vl_repopattern.R
import com.example.android_vl_repopattern.databinding.ListItemBinding
import com.example.android_vl_repopattern.model.dataModels.Item

class ItemApter(
    private val selectItem: (Long) -> Unit,
    private val navigateToEditFragment: () -> Unit
) : RecyclerView.Adapter<ItemApter.ItemViewHolder>() {

    private var dataset: List<Item> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Item>) {
        dataset = list
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(val vb: ListItemBinding) : RecyclerView.ViewHolder(vb.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        setupListItemView(holder, item)
        setItemOnClickListener(holder, item)
    }

    private fun setItemOnClickListener(
        holder: ItemViewHolder,
        item: Item
    ) {
        holder.vb.root.setOnClickListener {
            selectItem(item.id)
            navigateToEditFragment()
        }
    }

    private fun setupListItemView(
        holder: ItemViewHolder,
        item: Item
    ) {
        holder.vb.listText.text = item.title
        holder.vb.listImage.load(item.image) {
            error(R.drawable.ic_round_broken_image_24)
            transformations(RoundedCornersTransformation(10f))
        }
    }
}