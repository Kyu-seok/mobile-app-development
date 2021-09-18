package com.yeumkyuseok.recyclerviewpractice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String mName[], mDesc[];
    Context context;

    public MyAdapter(Context context, String name[], String description[]) {
        this.context = context;
        this.mName = name;
        this.mDesc = description;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textName.setText(mName[position]);
        holder.textDesc.setText(mDesc[position]);
        holder.imgAvatar.setImageResource(R.mipmap.ic_launcher);

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
    }

    @Override
    public int getItemCount() {
        return mName.length;    // number of elements in the recycler view to show
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textDesc;
        ImageView imgAvatar;
        ConstraintLayout mainLayout;    // mainLayout is used for clickListener

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textDesc = itemView.findViewById(R.id.textDescription);
            imgAvatar = itemView.findViewById(R.id.imageView2);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
