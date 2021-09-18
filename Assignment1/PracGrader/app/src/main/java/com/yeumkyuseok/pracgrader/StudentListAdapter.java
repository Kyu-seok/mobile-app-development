package com.yeumkyuseok.pracgrader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    String mNames[];
    double mMarks[];
    Context context;

    public StudentListAdapter(Context context, String[] mNames, double[] mMarks) {
        this.context = context;
        this.mNames = mNames;
        this.mMarks = mMarks;
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
        holder.textStudentMark.setText(mNames[position]);
        holder.textStudentMark.setText(Double.toString(mMarks[position]));
        holder.imgAvatar.setImageResource(R.drawable.student);

        if (mMarks[position] <= 50) {
            holder.cardView.setBackgroundColor(Color.parseColor("#ffff4444"));
        } else if (mMarks[position] <= 80) {
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
        return mNames.length;
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
