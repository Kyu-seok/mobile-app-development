package com.yeumkyuseok.mathtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmailListAdapter extends RecyclerView.Adapter <EmailListAdapter.EmailViewHolder> {

    List<String> stringList;
    Context context;

    public EmailListAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.email_row, parent, false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        holder.txtEmail.setText(stringList.get(position).toString());
    }


    @Override
    public int getItemCount() {
        if (stringList == null) {
            return 0;
        }
        return stringList.size();
    }

    public class EmailViewHolder extends RecyclerView.ViewHolder {
        TextView txtEmail;

        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmail = itemView.findViewById(R.id.textViewEamilRow);
        }
    }
}
