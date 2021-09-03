package com.yeumkyuseok.islandbuilder;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SelectFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private StructureData structures;
    private Structure selectedStructure = null;
    public Structure getSelectedStructure() {
        return selectedStructure;
    }

    public SelectFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.structures = StructureData.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new SelectAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class SelectViewHolder extends RecyclerView.ViewHolder{
        private Structure structure = null;
        private ImageView image;
        private TextView label;

        public SelectViewHolder(LayoutInflater inflater, ViewGroup view) {
            super(inflater.inflate(R.layout.list_selection, view, false));

            image = (ImageView) itemView.findViewById(R.id.structureImage);
            label = (TextView) itemView.findViewById(R.id.structureLabel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedStructure = structure;

                }
            });
        }

        public void bind(Structure structure) {
            this.structure = structure;
            image.setImageResource(structure.getDrawableId());
            label.setText(structure.getLabel());
        }

    }

    private class SelectAdapter extends RecyclerView.Adapter<SelectViewHolder> {
        @NonNull
        @Override
        public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SelectViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {
            holder.bind(structures.get(position));
        }

        @Override
        public int getItemCount() {
            return structures.size();
        }
    }
}
