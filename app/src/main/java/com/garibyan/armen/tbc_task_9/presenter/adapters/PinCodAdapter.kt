package com.garibyan.armen.tbc_task_9.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_task_9.databinding.PincodEmptyItemBinding
import com.garibyan.armen.tbc_task_9.databinding.PincodFilledItemBinding
import com.garibyan.armen.tbc_task_9.presenter.items.PinCodItem

class PinCodAdapter: ListAdapter<PinCodItem, RecyclerView.ViewHolder>(PinCodItemCallBAck()) {

    inner class EmptyPinCodViewHolder(private val binding: PincodEmptyItemBinding): RecyclerView.ViewHolder(binding.root){

    }
    inner class FilledPinCodViewHolder(private val binding: PincodFilledItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    class PinCodItemCallBAck: DiffUtil.ItemCallback<PinCodItem>(){
        override fun areItemsTheSame(oldItem: PinCodItem, newItem: PinCodItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: PinCodItem, newItem: PinCodItem) =
            oldItem == newItem

    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is EmptyPinCodViewHolder -> holder
            is FilledPinCodViewHolder -> holder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 -> EmptyPinCodViewHolder(PincodEmptyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> FilledPinCodViewHolder(PincodFilledItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }


}