package com.example.schedulerapp.ui.exam;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedulerapp.ui.exam.ExamInfo;

import java.util.ArrayList;
import java.util.List;

public class ExamViewModel extends ViewModel {

    private final MutableLiveData<List<ExamInfo>> examListLiveData;

    public ExamViewModel() {
        examListLiveData = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<ExamInfo>> getExamList() {
        return examListLiveData;
    }

    public void addExam(ExamInfo examInfo) {
        List<ExamInfo> currentList = examListLiveData.getValue();
        if (currentList != null) {
            currentList.add(examInfo);
            examListLiveData.setValue(currentList);
        }
    }

    public void updateExam(ExamInfo updatedExamInfo) {
        List<ExamInfo> currentList = examListLiveData.getValue();
        if (currentList != null) {
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getId() == updatedExamInfo.getId()) {
                    currentList.set(i, updatedExamInfo);
                    examListLiveData.setValue(currentList);
                    break;
                }
            }
        }
    }
}
