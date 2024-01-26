package com.example.schedulerapp.ui.todo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodoViewModel  extends ViewModel {
    private final MutableLiveData<String> mText;

    public TodoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is todo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
