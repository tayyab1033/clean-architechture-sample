package com.example.task_todoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_todoapp.model.Products
import com.example.task_todoapp.repository.DataRepositorySource
import com.example.task_todoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyListViewModel @Inject constructor(
    private val dataRepositorySource: DataRepositorySource
) : ViewModel() {
    var buyList: MutableLiveData<Resource<Products>> = MutableLiveData()

    fun buyList() {
        viewModelScope.launch {
            buyList.value = Resource.Loading()
            dataRepositorySource.requestProducts().collect() {
                buyList.value = it
            }
        }
    }
}