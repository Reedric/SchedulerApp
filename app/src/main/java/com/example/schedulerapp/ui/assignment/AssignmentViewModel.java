package com.example.schedulerapp.ui.assignment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AssignmentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AssignmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is assignments fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}