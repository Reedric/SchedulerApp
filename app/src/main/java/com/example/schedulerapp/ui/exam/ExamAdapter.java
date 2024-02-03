package com.example.schedulerapp.ui.exam;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.schedulerapp.R;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.ui.exam.ExamInfo;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private List<ExamInfo> examList;

    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewExamName;
        public TextView textViewExamLocation;
        public TextView textViewExamDate;
        public TextView textViewExamTime;

        public ExamViewHolder(View itemView) {
            super(itemView);
            textViewExamName = itemView.findViewById(R.id.textViewExamName);
            textViewExamLocation = itemView.findViewById(R.id.textViewExamLocation);
            textViewExamDate = itemView.findViewById(R.id.textViewExamDate);
            textViewExamTime = itemView.findViewById(R.id.textViewExamTime);
        }
    }

    public ExamAdapter(List<ExamInfo> examList) {
        this.examList = examList;
    }

    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_item, parent, false);
        return new ExamViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExamViewHolder holder, int position) {
        ExamInfo currentExam = examList.get(position);
        holder.textViewExamName.setText(currentExam.getName());
        holder.textViewExamLocation.setText(currentExam.getLocation());
        holder.textViewExamDate.setText(currentExam.getDate());
        holder.textViewExamTime.setText(currentExam.getTime());
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public void setExams(List<ExamInfo> exams) {
        examList = exams;
        notifyDataSetChanged();
    }
}
