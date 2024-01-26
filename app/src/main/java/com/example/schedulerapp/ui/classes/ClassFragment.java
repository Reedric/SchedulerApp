package com.example.schedulerapp.ui.classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulerapp.MainActivity;
import com.example.schedulerapp.databinding.FragmentClassBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ClassFragment extends Fragment {

    private FragmentClassBinding binding;

    private FloatingActionButton btn_add;

    private ArrayAdapter<ClassInfo> classInfoArrayAdapter;

    private final ActivityResultLauncher<Intent> startActivityForResult =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                if (data != null) {
                                    String className = data.getStringExtra("CLASS_NAME");
                                    String classStartTime = data.getStringExtra("CLASS_START_TIME");
                                    String classEndTime = data.getStringExtra("CLASS_END_TIME");
                                    String professorName = data.getStringExtra("PROFESSOR_NAME");

                                    ClassViewModel classViewModel = new ViewModelProvider(ClassFragment.this).get(ClassViewModel.class);
                                    classViewModel.addClassInfo(new ClassInfo(className, classStartTime, classEndTime, professorName));
                                }
                            }
                        }
                    });

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClassViewModel classViewModel =
                new ViewModelProvider(this).get(ClassViewModel.class);

        binding = FragmentClassBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView listView = binding.listViewClass;
        classInfoArrayAdapter = new ArrayAdapter<>(
                requireActivity(),
                android.R.layout.simple_list_item_1,
                new ArrayList<>()
        );

        listView.setAdapter(classInfoArrayAdapter);
        classViewModel.getClassInfoList().observe(getViewLifecycleOwner(), classInfo -> {
            classInfoArrayAdapter.clear();
            classInfoArrayAdapter.addAll(classInfo);
            classInfoArrayAdapter.notifyDataSetChanged();
        });

        btn_add = binding.btnAddClass;
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ClassPopUp.class);
                startActivityForResult.launch(i);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}