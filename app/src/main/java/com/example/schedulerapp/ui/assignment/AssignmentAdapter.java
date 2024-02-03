package com.example.schedulerapp.ui.assignment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import java.util.List;


public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {


    private List<AssignmentInfo> assignmentList;


    public AssignmentAdapter(List<AssignmentInfo> assignmentList) {
        this.assignmentList = assignmentList;
    }


    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        AssignmentInfo assignment = assignmentList.get(position);
        holder.assignmentName.setText(assignment.getName());
        holder.courseName.setText(assignment.getCourseName());
        holder.dueDate.setText(assignment.getDueDate());
    }


    @Override
    public int getItemCount() {
        return assignmentList.size();
    }


    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView assignmentName, courseName, dueDate;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentName = itemView.findViewById(R.id.assignment_name);
            courseName = itemView.findViewById(R.id.course_name);
            dueDate = itemView.findViewById(R.id.due_date);
        }
    }
}
