package com.yeumkyuseok.pracgrader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PracticalListAdapter extends RecyclerView.Adapter<PracticalListAdapter.PracticalViewHolder> {

    String names[];
    Double markScored[], markAssigned[];
    Context context;

    public PracticalListAdapter(Context context, String[] names, Double[] markScored, Double[] markAssigned) {
        this.names = names;
        this.markScored = markScored;
        this.markAssigned = markAssigned;
        this.context = context;
    }

    @NonNull
    @Override
    public PracticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.practical_row, parent, false);
        return new PracticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PracticalViewHolder holder, int position) {

        holder.textPracticalName.setText(names[position]);
        holder.textMarkScored.setText(Double.toString(markScored[position]));
        holder.textMarkAssigned.setText(Double.toString(markAssigned[position]));


        // TODO : impelement setOnClickListner and move intent to EDIT PRactical info
        /*
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("mName", mName[position]);
                intent.putExtra("mDesc", mDesc[position]);
                intent.putExtra("imgAvatar", R.mipmap.ic_launcher);
                context.startActivity(intent);
            }
        });
         */
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class PracticalViewHolder extends RecyclerView.ViewHolder {

        TextView textPracticalName, textMarkScored, textMarkAssigned;
        ImageView imgPractical;
        CardView cardView;
        ConstraintLayout practicalLayout;    // mainLayout is used for clickListener

        public PracticalViewHolder(@NonNull View itemView) {
            super(itemView);
            textPracticalName = itemView.findViewById(R.id.textPracticalName);
            textMarkScored = itemView.findViewById(R.id.textMarkScored);
            textMarkAssigned = itemView.findViewById(R.id.textMarksAssigned);
            imgPractical = itemView.findViewById(R.id.imgPractical);
            cardView = itemView.findViewById(R.id.cardViewPractical);
            practicalLayout = itemView.findViewById(R.id.practicalLayout);

        }
    }
}
