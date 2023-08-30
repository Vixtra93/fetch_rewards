package com.project.fetchrewards.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.project.fetchrewards.R
import com.project.fetchrewards.databinding.ItemRewardsBinding
import com.project.fetchrewards.remote.models.Item

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rewards, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

}

class ItemViewHolder(view: View) : ViewHolder(view) {
    val binding = ItemRewardsBinding.bind(view)

    fun bind(item: Item) {
        binding.apply {
            txvId.text = itemView.context.getString(R.string.id, item.id.toString())
            txvListId.text = itemView.context.getString(R.string.listid, item.listId.toString())
            txvName.text = itemView.context.getString(R.string.name, item.name)
        }
    }

}
