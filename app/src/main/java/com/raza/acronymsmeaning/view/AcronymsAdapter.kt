package com.raza.acronymsmeaning.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raza.acronymsmeaning.databinding.RvItemAdapterBinding

class AcronymsAdapter : RecyclerView.Adapter<AcronymsViewHolder>() {

    private var fullFormsList = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemAdapterBinding.inflate(inflater, parent, false)
        return AcronymsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AcronymsViewHolder, position: Int) {
        val largeForm = fullFormsList[position]
        holder.binding.tvAbbr.text = largeForm
    }

    override fun getItemCount(): Int {
        return fullFormsList.size
    }

    fun setData(lfs: List<String>) {
        this.fullFormsList = lfs.toMutableList()
        notifyDataSetChanged()
    }
}

class AcronymsViewHolder(val binding: RvItemAdapterBinding) : RecyclerView.ViewHolder(binding.root)