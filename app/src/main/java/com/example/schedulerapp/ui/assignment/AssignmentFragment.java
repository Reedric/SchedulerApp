package com.example.schedulerapp.ui.assignment;

import android.app.Activity;
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
import androidx.recyclerview.widget.RecyclerView;
import com.example.schedulerapp.databinding.FragmentAssignmentBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {
    private FragmentAssignmentBinding binding;
    private RecyclerView recyclerView;
    private AssignmentAdapter adapter;
    private List<AssignmentInfo> assignmentList;
    private FloatingActionButton btn_assignment_add;

    private ActivityResultLauncher<Intent> assignmentPopUpLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assignmentPopUpLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String name = data.getStringExtra("assignmentName");
                            String courseName = data.getStringExtra("courseName");
                            String dueDate = data.getStringExtra("dueDate");

                            AssignmentInfo newAssignment = new AssignmentInfo(name, courseName, dueDate);
                            assignmentList.add(newAssignment);
                            adapter.notifyItemInserted(assignmentList.size() - 1);
                        }
                    }
                });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAssignmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initializeRecyclerView();
        setupAddButton();

        return root;
    }

    private void initializeRecyclerView() {
        btn_assignment_add = binding.btnAddAssignment;
        recyclerView = binding.assignmentsRecyclerView;
        assignmentList = new ArrayList<>();
        adapter = new AssignmentAdapter(assignmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupAddButton() {
        btn_assignment_add.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AssignmentPopUp.class);
            assignmentPopUpLauncher.launch(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

