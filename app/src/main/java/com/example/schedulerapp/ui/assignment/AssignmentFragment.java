package com.example.schedulerapp.ui.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulerapp.databinding.FragmentAssignmentBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AssignmentFragment extends Fragment {

    private FragmentAssignmentBinding binding;

    private FloatingActionButton btn_add;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AssignmentViewModel assignmentViewModel =
                new ViewModelProvider(this).get(AssignmentViewModel.class);

        binding = FragmentAssignmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_add = binding.btnAddAssignment;
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AssignmentPopUp.class);
                startActivity(i);
            }
        });

        final TextView textView = binding.textAssignment;
        assignmentViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}