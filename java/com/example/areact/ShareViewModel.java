package com.example.areact;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {
    private final MutableLiveData liveData = new MutableLiveData();

    public LiveData getLiveData() {
        return liveData;
    }

    public void setLiveData(String str) {
        liveData.setValue(str);
    }
}
