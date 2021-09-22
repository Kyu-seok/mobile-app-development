package com.yeumkyuseok.pracgrader;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class StaffPracticalListAdapter extends RecyclerView.Adapter<StaffPracticalListAdapter.StaffPracViewHolder> {
    private static final String TAG = "StaffPracticalListAdpt";

    List<Practical> practicals;
    Context context;
    Data data = new Data();

    public StaffPracticalListAdapter(Context context, List<Practical> practicals) {
        this.practicals = practicals;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffPracViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.staff_prac_row, parent, false);
        data.load(context);
        return new StaffPracViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffPracViewHolder holder, int position) {
        Practical practical = practicals.get(position);
        Log.d(TAG, "onBindViewHolder: practicals(" + position + ") Title :" + practical.getTitle());
        Log.d(TAG, "onBindViewHolder: practicals(" + position + ") mark :" + practical.getMark());
        Log.d(TAG, "onBindViewHolder: practicals(" + position + ") desc :" + practical.getDescription());
        holder.textStaffPracTitle.setText("Title : " + practical.getTitle());
        holder.textStaffPracTotalMark.setText("Mark weigh : " + practical.getMark());
        holder.textStaffPracDesc.setText("Description : \n" + practical.getDescription());
        holder.cardViewStaffPrac.setBackgroundColor(Color.parseColor("#FFF5EE"));
        holder.cardViewStaffPrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SinglePracActivity.class);
                intent.putExtra("title", practical.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return practicals.size();
    }

    public class StaffPracViewHolder extends RecyclerView.ViewHolder {

        TextView textStaffPracTitle, textStaffPracTotalMark, textStaffPracDesc;
        ImageView imgStaffPrac;
        CardView cardViewStaffPrac;

        public StaffPracViewHolder(@NonNull View itemView) {
            super(itemView);
            textStaffPracTitle = (TextView) itemView.findViewById(R.id.textStaffPracTitle);
            textStaffPracTotalMark = itemView.findViewById(R.id.textStaffPracTotalMark);
            textStaffPracDesc = itemView.findViewById(R.id.textStaffPracDesc);
            imgStaffPrac = itemView.findViewById(R.id.imageStaffPrac);
            cardViewStaffPrac = itemView.findViewById(R.id.cardViewStaffPrac);
        }
    }
}
