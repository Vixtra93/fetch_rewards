package com.project.fetchrewards.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fetchrewards.utils.Resource
import com.project.fetchrewards.remote.models.Item
import com.project.fetchrewards.repository.FetchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FetchRepository
) : ViewModel() {

    private var _items = MutableLiveData<Map<Int, List<Item>>>()
    private var _isLoading = MutableLiveData<Boolean>()
    private var _errorMessage = MutableLiveData<String>()
    val items: MutableLiveData<Map<Int, List<Item>>> get() = _items
    val isLoading: MutableLiveData<Boolean> get() = _isLoading
    val errorMessage: MutableLiveData<String> get() = _errorMessage

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {

            val response = repository.getItems()

            when (response.status) {

                Resource.Status.SUCCESS -> {
                    _isLoading.postValue(false)
                    if (response.data.isNullOrEmpty()) {
                        _errorMessage.postValue("Ocurrió un error")
                    } else {
                        val itemsList =
                            response.data.sortedWith(compareBy(Item::listId, Item::name))
                                .filterNot { it.name.isNullOrEmpty() }.groupBy { it.listId }
                        _items.postValue(itemsList)
                    }
                }

                Resource.Status.ERROR -> {
                    response.message?.let {
                        _errorMessage.postValue(it)
                    }
                }

                Resource.Status.LOADING -> {
                    _isLoading.postValue(true)
                }
            }
        }
    }

}
