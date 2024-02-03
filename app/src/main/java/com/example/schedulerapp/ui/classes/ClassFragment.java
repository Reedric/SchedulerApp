package com.example.schedulerapp.ui.classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulerapp.databinding.FragmentClassBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassFragment extends Fragment {

    private FragmentClassBinding binding;

    private ArrayAdapter<ClassInfo> classInfoArrayAdapter;

    private ClassViewModel classViewModel;

    private final ActivityResultLauncher<Intent> startActivityForResult =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                if (data != null) {
                                    if (data.getBooleanExtra("FROM_EDIT_CLASS_POPUP", false)) {
                                        // Handle result from EditClassPopUp
                                        Serializable originalClass = data.getSerializableExtra("ORIGINAL_CLASS_INFO");
                                        if (originalClass instanceof ClassInfo) {
                                            ClassInfo originalClassInfo = (ClassInfo) originalClass;
                                            classViewModel = new ViewModelProvider(ClassFragment.this).get(ClassViewModel.class);
                                            classViewModel.deleteClassInfo(originalClassInfo);
                                        }
                                        if (data.getBooleanExtra("CONFIRMED_EDIT", false)) {
                                            Serializable editedClass = data.getSerializableExtra("EDITED_CLASS_INFO");
                                            if (editedClass instanceof ClassInfo) {
                                                ClassInfo editedClassInfo = (ClassInfo) editedClass;
                                                classViewModel.addClassInfo(editedClassInfo);
                                            }
                                        }
                                    } else {
                                        // Handle result from ClassPopUp
                                        Serializable serializable = data.getSerializableExtra("ADDED_CLASS_INFO");
                                        if (serializable instanceof ClassInfo) {
                                            ClassInfo addedClassInfo = (ClassInfo) serializable;
                                            ClassViewModel classViewModel = new ViewModelProvider(ClassFragment.this).get(ClassViewModel.class);
                                            classViewModel.addClassInfo(addedClassInfo);
                                        }
                                    }
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

        FloatingActionButton btn_add = binding.btnAddClass;
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ClassPopUp.class);
                startActivityForResult.launch(i);
            }
        });

        // Set OnItemClickListener for listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassInfo selectedClass = classInfoArrayAdapter.getItem(position);

                Intent editIntent = new Intent(getContext(), EditClassPopUp.class);
                assert selectedClass != null;
                editIntent.putExtra("SEL_EDITED_CLASS_INFO", selectedClass);
                startActivityForResult.launch(editIntent);
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