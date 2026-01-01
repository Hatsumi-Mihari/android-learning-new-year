package com.example.myapplication;

import androidx.lifecycle.ViewModel;

import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

public class CounterViewModel extends ViewModel {
    private final MutableStateFlow<CounterState> _state = StateFlowKt.MutableStateFlow(new CounterState(0, false, System.currentTimeMillis()));

    public StateFlow<CounterState> getState() {
        return _state;
    }

    public void increment() {
        _state.setValue(new CounterState(_state.getValue().getCount() + 1, false, _state.getValue().getFirstTimeStart()));
    }

    public void forceUpdateTime(){
        _state.setValue(new CounterState(
                _state.getValue().getCount(),
                !_state.getValue().getStateUpdateTime(),
                _state.getValue().getFirstTimeStart()
                ));
    }

    public long getTimeFromStart(){
        return _state.getValue().getTimeFromStart();
    }
}
