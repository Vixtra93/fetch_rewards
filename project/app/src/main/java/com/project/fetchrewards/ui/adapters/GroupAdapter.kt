package com.project.fetchrewards.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.fetchrewards.R
import com.project.fetchrewards.databinding.ItemGroupBinding
import com.project.fetchrewards.remote.models.Item

class GroupAdapter :
    RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    private val groupedItems = mutableMapOf<Int, List<Item>>()
    private lateinit var context: Context
    fun setItems(groupedItems: Map<Int, List<Item>>) {
        this.groupedItems.clear()
        this.groupedItems.putAll(groupedItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        context = parent.context
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val (listId, items) = groupedItems.entries.elementAt(position)
        holder.bind(listId, items)
    }

    override fun getItemCount(): Int {
        return groupedItems.size
    }

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemGroupBinding.bind(itemView)

        fun bind(listId: Int, items: List<Item>) {
            binding.apply {
                groupTitle.text = context.getString(R.string.list_id, listId.toString())
                groupRecyclerView.apply {
                    layoutManager = LinearLayoutManager(itemView.context)
                    adapter = ItemAdapter(items)
                    addItemDecoration(
                        DividerItemDecoration(
                            context,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                }

            }
        }
    }
}
