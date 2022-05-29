package com.mvvm.fithelperapp.ui.mainactivity

import android.util.Log
import androidx.lifecycle.*
import com.mvvm.fithelperapp.util.ApiCallState
import com.mvvm.fithelperapp.util.EventResultConsts

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mainEventChannel =  Channel<MainEvent>()
    val mainEvent = mainEventChannel.receiveAsFlow()

    private val _dataState: MutableLiveData<ApiCallState> = MutableLiveData()

    val dataState: LiveData<ApiCallState>
        get() = _dataState


    fun onDbUpdateRequired(){
        viewModelScope.launch {
            mainRepository.getRecordsFromNetwork().onEach {
                dataState ->
                _dataState.value = dataState
            }
                .launchIn(viewModelScope)
        }
    }

    fun onApiCallSuccess(){
        viewModelScope.launch {
            mainEventChannel.send(MainEvent.ShowUpdateResultMessage(EventResultConsts.REQUIRE_UPDATE_RESULT_SUCCESS))
            mainEventChannel.send(MainEvent.HideProgressBar)
        }
    }

    fun onApiCallError(e: HttpException){
        viewModelScope.launch {


            mainEventChannel.send(MainEvent.ShowUpdateResultMessage(EventResultConsts.REQUIRE_UPDATE_RESULT_UNKNOWN_ERROR + e.message))
            mainEventChannel.send(MainEvent.HideProgressBar)
        }
    }

    fun onApiCallLoading(){
        viewModelScope.launch {
            mainEventChannel.send(MainEvent.ShowProgressBar)
        }
    }
}

sealed class MainEvent {

    data class ShowUpdateResultMessage(val msg: String) : MainEvent()

    object ShowProgressBar : MainEvent()

    object HideProgressBar : MainEvent()
}