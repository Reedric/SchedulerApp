package com.example.schedulerapp.ui.classes;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        sortClassList(currentList);

        classInfoLiveData.setValue(currentList);
    }

    public void deleteClassInfo(ClassInfo classInfo) {
        List<ClassInfo> currentList = classInfoLiveData.getValue();
        int idToRemove = classInfo.getId();

        // iterate through currentList and delete ClassInfo object with matching id
        if (currentList != null) {
            Iterator<ClassInfo> iterator = currentList.iterator();
            while (iterator.hasNext()) {
                ClassInfo existingClassInfo = iterator.next();
                if (existingClassInfo.getId() == idToRemove) {
                    iterator.remove();
                    break;
                }
            }

            sortClassList(currentList);
            classInfoLiveData.setValue(currentList);
        }
    }

    public void sortClassList(List<ClassInfo> currentList) {
        currentList.sort(new Comparator<ClassInfo>() {
            @Override
            public int compare(ClassInfo class1, ClassInfo class2) {
                SimpleDateFormat sdf = new SimpleDateFormat("h:mma", Locale.US);
                try {
                    Date time1 = sdf.parse(class1.getStartTime());
                    Date time2 = sdf.parse(class2.getStartTime());
                    return time1.compareTo(time2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }
}