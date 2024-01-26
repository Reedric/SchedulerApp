package com.example.schedulerapp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedulerapp.ClassInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<ClassInfo>> classInfoLiveData;

    public HomeViewModel() {
        classInfoLiveData = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<ClassInfo>> getClassInfoList() {
        Log.d("HomeViewModel", "Observers notified of classInfoLiveData changes");
        return classInfoLiveData;
    }

    public void addClassInfo(ClassInfo classInfo) {
        List<ClassInfo> currentList = classInfoLiveData.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(classInfo);
        classInfoLiveData.setValue(currentList);
        Log.d("HomeViewModel", "Added ClassInfo: " + classInfo.toString());

    }
}
