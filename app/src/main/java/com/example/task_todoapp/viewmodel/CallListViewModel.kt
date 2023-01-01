package com.example.task_todoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_todoapp.model.Persons
import com.example.task_todoapp.repository.DataRepositorySource
import com.example.task_todoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallListViewModel @Inject constructor(private val dataRepositorySource: DataRepositorySource) :
    ViewModel() {
    var callList: MutableLiveData<Resource<Persons>> = MutableLiveData()

    fun callList() {
        viewModelScope.launch {
            callList.value = Resource.Loading()
            dataRepositorySource.requestPersons().collect() {
                callList.value = it
            }
        }
    }
}