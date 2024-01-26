package com.example.schedulerapp.ui.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulerapp.databinding.FragmentExamBinding;

public class ExamFragment extends Fragment {

    private FragmentExamBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExamViewModel examViewModel =
                new ViewModelProvider(this).get(ExamViewModel.class);

        binding = FragmentExamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textExam;
        examViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}