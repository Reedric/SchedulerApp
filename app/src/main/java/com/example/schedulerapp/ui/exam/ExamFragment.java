package com.example.schedulerapp.ui.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.schedulerapp.databinding.FragmentExamBinding;

import java.util.ArrayList;

public class ExamFragment extends Fragment {

    private FragmentExamBinding binding;
    private ExamViewModel examViewModel;
    private ExamAdapter examAdapter;

    private ActivityResultLauncher<Intent> examPopUpLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        examViewModel = new ViewModelProvider(requireActivity()).get(ExamViewModel.class);

        examPopUpLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        String examName = result.getData().getStringExtra("examName");
                        String examLocation = result.getData().getStringExtra("examLocation");
                        String examDate = result.getData().getStringExtra("examDate");
                        String examTime = result.getData().getStringExtra("examTime");
                        ExamInfo newExam = new ExamInfo(examName, examLocation, examDate, examTime);
                        examViewModel.addExam(newExam);
                    }
                }
        );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        examAdapter = new ExamAdapter(new ArrayList<>());
        binding.examList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.examList.setAdapter(examAdapter);

        binding.btnAddExam.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ExamPopUp.class);
            examPopUpLauncher.launch(intent);
        });

        examViewModel.getExamList().observe(getViewLifecycleOwner(), exams -> {
            examAdapter.setExams(exams);
            examAdapter.notifyDataSetChanged();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
