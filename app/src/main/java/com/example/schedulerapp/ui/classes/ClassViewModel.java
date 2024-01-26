package com.example.schedulerapp.ui.classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClassViewModel extends ViewModel {

    private final MutableLiveData<List<ClassInfo>> classInfoLiveData;

    public ClassViewModel() {
        classInfoLiveData = new MutableLiveData<>();
    }

    public LiveData<List<ClassInfo>> getClassInfoList() {
        return classInfoLiveData;
    }

    public void addClassInfo(ClassInfo classInfo) {
        List<ClassInfo> currentList = classInfoLiveData.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(classInfo);
        classInfoLiveData.setValue(currentList);
    }

}