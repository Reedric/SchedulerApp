package com.example.schedulerapp.ui.classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulerapp.databinding.FragmentClassBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClassFragment extends Fragment {

    private FragmentClassBinding binding;

    private FloatingActionButton btn_add;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClassViewModel classViewModel =
                new ViewModelProvider(this).get(ClassViewModel.class);

        binding = FragmentClassBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_add = binding.btnAddClass;
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ClassPopUp.class);
                startActivity(i);
            }
        });

        final TextView textView = binding.textClass;
        classViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}