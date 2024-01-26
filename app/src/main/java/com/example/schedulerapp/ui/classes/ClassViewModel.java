package com.example.schedulerapp.ui.classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClassViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ClassViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is classes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}