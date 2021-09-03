package com.yeumkyuseok.islandbuilder;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MapFragment extends Fragment {
    private SelectFragment selector = null;
    private MapData map;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public MapFragment() {

    }

    public void setSelector(SelectFragment selector) {
        this.selector = selector;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.map = MapData.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.mapRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MapData.HEIGHT, GridLayoutManager.HORIZONTAL, false));
        adapter = new MapAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class MapViewHolder extends RecyclerView.ViewHolder{
        private MapElement element;
        private ImageView northWestImg;
        private ImageView northEastImg;
        private ImageView southWestImg;
        private ImageView southEastImg;
        private ImageView structureImg;

        public MapViewHolder(LayoutInflater inflater, ViewGroup view) {
            super(inflater.inflate(R.layout.grid_cell, view, false));

            northWestImg = (ImageView) itemView.findViewById(R.id.northWest);
            northEastImg = (ImageView) itemView.findViewById(R.id.northEast);
            southWestImg = (ImageView) itemView.findViewById(R.id.southWest);
            southEastImg = (ImageView) itemView.findViewById(R.id.southEast);
            structureImg = (ImageView) itemView.findViewById(R.id.structure);
            int size = view.getMeasuredHeight()/MapData.HEIGHT + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Structure newStructure = selector.getSelectedStructure();
                    if (newStructure != null && element.isBuildable()) {
                        element.setStructure(StructureData.get().get(0));
                        element.setStructure(newStructure);
                        adapter.notifyItemChanged(getAdapterPosition());
                    }
                }
            });
        }

        public void bind(MapElement element) {
            this.element = element;
            northWestImg.setImageResource(element.getNorthWest());
            northEastImg.setImageResource(element.getNorthEast());
            southWestImg.setImageResource(element.getSouthWest());
            southEastImg.setImageResource(element.getSouthEast());

            Structure structure = element.getStructure();
            if (structure == null) {
                structureImg.setVisibility(View.INVISIBLE);
            } else {
                structureImg.setVisibility(View.VISIBLE);
                structureImg.setImageResource(structure.getDrawableId());
            }
        }


    }

    private class MapAdapter extends RecyclerView.Adapter<MapViewHolder> {
        @NonNull
        @Override
        public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MapViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MapViewHolder holder, int position) {
            int row = position % MapData.HEIGHT;
            int col = position / MapData.HEIGHT;
            holder.bind(map.get(row, col));
        }

        @Override
        public int getItemCount() {
            return MapData.WIDTH * MapData.HEIGHT;
        }
    }
}
