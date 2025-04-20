package com.learning.talentaisproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.talentaisproject.database.entity.StatusEntity
import com.learning.talentaisproject.databinding.ListStatusBinding

class StatusAdapter(
    private val onEditClick: (StatusEntity) -> Unit,
    private val onDeleteClick: (StatusEntity) -> Unit
) : RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    private val statusList = mutableListOf<StatusEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<StatusEntity>) {
        statusList.clear()
        statusList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder =
        StatusViewHolder(ListStatusBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))


    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.bind(statusList[position])
    }

    override fun getItemCount() = statusList.size

    inner class StatusViewHolder(
        private val binding: ListStatusBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(status: StatusEntity) {
            binding.apply {
                tvUsername.text = status.username
                tvStatus.text = status.content

                btnEdit.setOnClickListener { onEditClick(status) }
                btnDelete.setOnClickListener { onDeleteClick(status) }
            }
        }
    }
}