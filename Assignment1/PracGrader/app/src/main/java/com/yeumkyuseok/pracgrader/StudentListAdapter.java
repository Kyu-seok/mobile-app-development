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

    // String mNames[];
    // double mMarks[];
    Context context;

    public StudentListAdapter(Context context, List<User> tempUsers) {
        this.context = context;
        this.tempUsers = tempUsers;
        // constructor
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_row, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // holder.textStudentMark.setText(mNames[position]);
        // holder.textStudentMark.setText(Double.toString(mMarks[position]));
        Log.d(TAG, "onBindViewHolder getName() :" + tempUsers.get(position).getName());
        Log.d(TAG, "onBindViewHolder getPercentage: " + tempUsers.get(position).getPercentage());
        holder.textStudentName.setText(tempUsers.get(position).getName());
        // TODO: Calculate average mark of the student
        holder.textStudentMark.setText(Double.toString(tempUsers.get(position).getPercentage()));
        holder.imgAvatar.setImageResource(R.drawable.student);

        if (tempUsers.get(position).getPercentage() <= 50) {
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

                // TODO : pass the student ID value to SingleStudentActivity.class using putExtra()
                // intent.putExtra("mName", mName[position]);
                // intent.putExtra("mDesc", mDesc[position]);
                // intent.putExtra("imgAvatar", R.mipmap.ic_launcher);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tempUsers.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView textStudentName, textStudentMark;
        ImageView imgAvatar;
        CardView cardView;
        ConstraintLayout studentLayout;    // mainLayout is used for clickListener

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textStudentName = itemView.findViewById(R.id.textStudentName);
            textStudentMark = itemView.findViewById(R.id.textStudentMark);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            cardView = itemView.findViewById(R.id.cardViewStudent);
            studentLayout = itemView.findViewById(R.id.studentLayout);

        }
    }
}
