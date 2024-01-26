package com.example.schedulerapp.ui.exam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExamViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ExamViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is exams fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}