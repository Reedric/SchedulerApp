package com.example.schedulerapp.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulerapp.databinding.FragmentTodoBinding;

public class TodoFragment extends Fragment {

    private FragmentTodoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TodoViewModel todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        binding = FragmentTodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTodo;
        todoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
