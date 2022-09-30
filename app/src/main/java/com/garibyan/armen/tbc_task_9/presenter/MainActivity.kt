package com.garibyan.armen.tbc_task_9.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_task_9.databinding.ActivityMainBinding
import com.garibyan.armen.tbc_task_9.presenter.adapters.KeyboardAdapter
import com.garibyan.armen.tbc_task_9.presenter.adapters.PinCodAdapter
import com.garibyan.armen.tbc_task_9.presenter.items.PinCodItem
import com.garibyan.armen.tbc_task_9.presenter.utils.Constants
import com.garibyan.armen.tbc_task_9.presenter.utils.keyboardItemList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val keyboardAdapter: KeyboardAdapter by lazy { KeyboardAdapter() }
    private val pinCodeAdapter: PinCodAdapter by lazy { PinCodAdapter() }

    private val flow = MutableStateFlow("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        onClickListeners()
        observer()
    }

    private fun onClickListeners() = with(binding) {
        keyboardAdapter.onNumberClickListener = {
            flow.value = flow.value + it.toString()
        }
        keyboardAdapter.onClearClickListener = {
            flow.value = flow.value.dropLast(1)
        }

        passCode.setPasscodeEntryListener {
            if(flow.value == Constants.CURRECT_PIN_COD){
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                flow.value = ""
            }else{
                Toast.makeText(this@MainActivity, "Incorrect Pin Cod", Toast.LENGTH_SHORT).show()
                flow.value = ""
            }
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            flow.collect {
                binding.passCode.setText(it)
            }
        }
    }

    private fun initRecyclerView() = with(binding.recyclerviewKeyboard) {
        val customGridLayoutManager = object : GridLayoutManager(
            this@MainActivity,
            3,
            GridLayoutManager.VERTICAL,
            false
        ) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        adapter = keyboardAdapter
        layoutManager = customGridLayoutManager
        keyboardAdapter.submitList(keyboardItemList)
    }

}