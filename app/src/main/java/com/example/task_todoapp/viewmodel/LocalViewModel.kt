package com.example.task_todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_todoapp.model.Product
import com.example.task_todoapp.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    var productList: MutableLiveData<List<Product>> = MutableLiveData()

    fun fetchProducts() {
        viewModelScope.launch {
            localRepository.getData()
                .run {
                    productList.value = this
                }
        }
    }

    fun insertList(item: List<Product>) {
        viewModelScope.launch {
            localRepository.insertProduct(item)
                .run {
                    Log.d("tayyab", "insertList: SUCCESS")
                }
        }
    }
}