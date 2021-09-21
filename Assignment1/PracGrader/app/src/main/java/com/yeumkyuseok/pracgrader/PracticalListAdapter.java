package com.yeumkyuseok.pracgrader;

import android.content.Context;
import android.content.Intent;
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

public class PracticalListAdapter extends RecyclerView.Adapter<PracticalListAdapter.PracticalViewHolder> {

    List<TakenPrac> takenPracs;
    Context context;
    Data data = new Data();
    String username;

    public PracticalListAdapter(Context context, List<TakenPrac> takenPracs, String username) {
        this.takenPracs = takenPracs;
        this.context = context;
        this.username = username;
    }

    @NonNull
    @Override
    public PracticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.practical_row, parent, false);
        data.load(context);
        return new PracticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PracticalViewHolder holder, int position) {

        TakenPrac prac = takenPracs.get(position);
        holder.textPracticalName.setText(prac.getPracTitle());
        holder.textMarkScored.setText(Double.toString(prac.getMarkScored()));
        holder.textMarkAssigned.setText(Double.toString(data.getPracMarkAvailable(prac.pracTitle)));


        holder.practicalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CRUDResultActivity.class);
                intent.putExtra("mode", 1); //edit mode
                intent.putExtra("username", username);
                intent.putExtra("pracTitle", prac.getPracTitle());
                intent.putExtra("currMark", prac.getMarkScored());
                // intent.putExtra("mName", mName[position]);
                // intent.putExtra("mDesc", mDesc[position]);
                // intent.putExtra("imgAvatar", R.mipmap.ic_launcher);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return takenPracs.size();
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
