package com.example.myapplication;

import android.os.SystemClock;

public class CounterState {

    private final int count;
    private final long timeStart;
    private boolean updateTime = false;

    public CounterState(int count, boolean updateTime, long timeStart) {
        this.count = count;
        this.updateTime = updateTime;
        this.timeStart = timeStart;
    }

    public int getCount() {
        return count;
    }

    public long getTimeFromStart(){
        return System.currentTimeMillis() - this.timeStart;
    }

    public long getFirstTimeStart(){
        return this.timeStart;
    }

    public boolean getStateUpdateTime(){
        return this.updateTime;
    }
}
