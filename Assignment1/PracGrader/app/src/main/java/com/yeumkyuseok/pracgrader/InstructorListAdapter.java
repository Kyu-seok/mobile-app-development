package com.yeumkyuseok.pracgrader;

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

import java.util.List;

public class InstructorListAdapter extends RecyclerView.Adapter<InstructorListAdapter.InstructorViewHolder> {

    List<User> tempUsers;
    Context context;

    public InstructorListAdapter(Context context, List<User> tempUsers) {
        this.tempUsers = tempUsers;
        this.context = context;
    }


    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.instructor_row, parent, false);
        return new InstructorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
        User instructor = tempUsers.get(position);
        holder.textName.setText("Name : " + instructor.getName());
        holder.textUsername.setText("Username : " + instructor.getUser_name());
        holder.textPW.setText("Password : " + instructor.getPassword());
        holder.textEmail.setText("Email : " + instructor.getEmail());
        holder.textCountry.setText("Country : " + instructor.getCountry());
        holder.avatar.setImageResource(R.drawable.teacher);
        holder.cardView.setBackgroundColor(Color.parseColor("#FFF5EE"));
        holder.instructorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleInstrActivity.class);
                intent.putExtra("mode", 1);
                intent.putExtra("username", instructor.getUser_name());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return tempUsers.size();
    }

    public class InstructorViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textUsername, textPW, textEmail, textCountry;
        ImageView avatar;
        CardView cardView;
        ConstraintLayout instructorLayout;

        public InstructorViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textInstrName);
            textUsername = itemView.findViewById(R.id.textInstrUsername);
            textPW = itemView.findViewById(R.id.textInstrPW);
            textEmail = itemView.findViewById(R.id.textInstrEmail);
            textCountry = itemView.findViewById(R.id.textInstrCountry);
            avatar = itemView.findViewById(R.id.imageTeacher);
            cardView = itemView.findViewById(R.id.cardViewInstructor);
            instructorLayout = itemView.findViewById(R.id.instructorLayout);

        }
    }
}
