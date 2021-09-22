package com.yeumkyuseok.pracgrader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private static final String TAG = "StudentListAdapter";
    List<User> tempUsers;

    Context context;

    public StudentListAdapter(Context context, List<User> tempUsers) {
        this.context = context;
        this.tempUsers = tempUsers;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: enters!");
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_row, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textStudentName.setText(tempUsers.get(position).getName());
        holder.textStudentMark.setText(Double.toString(tempUsers.get(position).getPercentage())+"%");
        holder.imgAvatar.setImageResource(R.drawable.student);

        if (tempUsers.get(position).getTotalMarkAvailable() == 0) {
            holder.cardView.setBackgroundColor(Color.parseColor("#FFF5EE"));
            holder.textStudentMark.setText("No Practicals Taken");
        } else if (tempUsers.get(position).getPercentage() <= 50) {
            holder.cardView.setBackgroundColor(Color.parseColor("#ffff4444"));
        } else if (tempUsers.get(position).getPercentage() <= 80) {
            holder.cardView.setBackgroundColor(Color.parseColor("#FEE227"));
        } else {
            holder.cardView.setBackgroundColor(Color.parseColor("#43c465"));
        }

        holder.studentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleStudentActivity.class);
                String username = tempUsers.get(position).getUser_name();
                intent.putExtra("username", username);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tempUsers.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView textStudentName, textStudentMark, textUsername;
        ImageView imgAvatar;
        CardView cardView;
        ConstraintLayout studentLayout;    // mainLayout is used for clickListener

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textStudentName = itemView.findViewById(R.id.textStudentName);
            textStudentMark = itemView.findViewById(R.id.textStudentMark);
            textUsername = itemView.findViewById(R.id.textUserName);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            cardView = itemView.findViewById(R.id.cardViewStudent);
            studentLayout = itemView.findViewById(R.id.studentLayout);

        }
    }
}
