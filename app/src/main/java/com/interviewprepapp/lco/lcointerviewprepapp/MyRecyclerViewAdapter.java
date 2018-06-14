package com.interviewprepapp.lco.lcointerviewprepapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by anandsankritya on 11/06/18.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<QuestionsAndAnswers> questionsList;
    private Context context;

    public MyRecyclerViewAdapter(Context context, ArrayList<QuestionsAndAnswers> questionsList) {
        this.questionsList = questionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.questionTv.setText(questionsList.get(position).getQuestion());
        holder.questionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnswerActivity.class);
                intent.putExtra("question", questionsList.get(position).getQuestion());
                intent.putExtra("answer", questionsList.get(position).getAnswer());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView questionTv;
        public ViewHolder(View itemView) {
            super(itemView);
            questionTv = itemView.findViewById(R.id.questions_tv);
        }
    }
}
