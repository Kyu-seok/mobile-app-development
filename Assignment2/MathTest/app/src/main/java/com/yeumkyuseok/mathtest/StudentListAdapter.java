package com.yeumkyuseok.mathtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import java.io.Serializable;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private static final String TAG = "StudentListAdapter";
    List<Student> tempStudents;

    Context context;

    public StudentListAdapter(Context context, List<Student> tempStudents) {
        this.context = context;
        this.tempStudents = tempStudents;
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
        holder.textStudentName.setText(tempStudents.get(position).getFullName());
        holder.textStudentMark.setText(tempStudents.get(position).getMark()+"%");
        //holder.imgAvatar.setImageResource(R.drawable.student);
        if (tempStudents.get(position).getPhoto() == null ) {
            Log.d(TAG, "onBindViewHolder: " + tempStudents.get(position).fullName + " photoPath : " + tempStudents.get(position).getPhoto());
            holder.imgAvatar.setImageResource(R.drawable.student);
        } else {
            Log.d(TAG, "onBindViewHolder: " + tempStudents.get(position).fullName + " photoPath : " + tempStudents.get(position).getPhoto());
            Bitmap bitmap = BitmapFactory.decodeFile(tempStudents.get(position).getPhoto());

            holder.imgAvatar.setImageBitmap(bitmap);
        }


        holder.studentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Test.class);
                Student clickedStudent = tempStudents.get(position);
                intent.putExtra("student", clickedStudent);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tempStudents.size();
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
            // textUsername = itemView.findViewById(R.id.textUserName);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            cardView = itemView.findViewById(R.id.cardViewStudent);
            studentLayout = itemView.findViewById(R.id.studentLayout);

        }
    }
}
