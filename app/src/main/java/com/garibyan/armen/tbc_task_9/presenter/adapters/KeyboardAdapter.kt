package com.garibyan.armen.tbc_task_9.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_task_9.databinding.ClearItemBinding
import com.garibyan.armen.tbc_task_9.databinding.FingerPrintItemBinding
import com.garibyan.armen.tbc_task_9.databinding.NumberItemBinding
import com.garibyan.armen.tbc_task_9.presenter.items.KeyboardItem
import com.garibyan.armen.tbc_task_9.presenter.utils.KeyboardViewType


class KeyboardAdapter: ListAdapter<KeyboardItem, RecyclerView.ViewHolder>(KeyboardItemCallBack()) {

    var onNumberClickListener: ((number: Int) -> Unit)? = null
    var onClearClickListener: (() -> Unit)? = null

    inner class NumberViewHolder(private val binding: NumberItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(keyboardItem: KeyboardItem)= with(binding){
            btnNumber.text = keyboardItem.number.toString()
            btnNumber.setOnClickListener { onNumberClickListener?.invoke(keyboardItem.number!!) }
        }
    }

    inner class FingerPrintViewHolder(private val binding: FingerPrintItemBinding): RecyclerView.ViewHolder(binding.root){
    }

    inner class ClearViewHOlder(private val binding: ClearItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.btnClear.setOnClickListener { onClearClickListener?.invoke() }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            KeyboardViewType.NUMBER.type -> NumberViewHolder(NumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            KeyboardViewType.FINGERPRINT.type -> FingerPrintViewHolder(FingerPrintItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ClearViewHOlder(ClearItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NumberViewHolder -> holder.bind(getItem(position))
            is FingerPrintViewHolder -> holder
            is ClearViewHOlder -> holder.bind()
        }
    }

    class KeyboardItemCallBack: DiffUtil.ItemCallback<KeyboardItem>(){
        override fun areItemsTheSame(oldItem: KeyboardItem, newItem: KeyboardItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: KeyboardItem, newItem: KeyboardItem) =
            oldItem == newItem

    }
}