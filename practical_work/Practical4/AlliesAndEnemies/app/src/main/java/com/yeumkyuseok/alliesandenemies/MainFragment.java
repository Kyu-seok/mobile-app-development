package com.yeumkyuseok.alliesandenemies;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment
{
    private FactionList factionList;

    private Spinner nameSpinner;
    private EditText nameEditor;
    private EditText strength;
    private Spinner relationship;
    private Button addButton;

    private RecyclerView rv;
    private FactionAdapter adapter;
    private LinearLayoutManager rvLayout;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        factionList = new FactionList();
        factionList.load(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b)
    {
        View view = li.inflate(R.layout.fragment_main, parent, false);

        // Get references to all major UI elements.
        nameSpinner = (Spinner) view.findViewById(R.id.factionNameSpinner);
        nameEditor = (EditText) view.findViewById(R.id.factionNameEditor);
        strength = (EditText) view.findViewById(R.id.strength);
        relationship = (Spinner) view.findViewById(R.id.relationship);
        addButton = (Button) view.findViewById(R.id.addButton);
        rv = (RecyclerView) view.findViewById(R.id.list);

        // Set the initial state of the 'New Faction' part of the UI.
        relationship.setSelection(Faction.DEFAULT_RELATIONSHIP);
        strength.setText(String.valueOf(Faction.DEFAULT_STRENGTH));
        nameSpinner.setSelection(0);
        nameEditor.setVisibility(View.VISIBLE);

        // We have two UI elements: a drop-down list (Spinner) and an editor (EditText). We want to
        // hide the editor *unless* the spinner's position is zero ("Custom..."). This event handler
        // arranges the the necessary state changes.
        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long rowId)
            {
                if(position == 0)
                {
                    nameEditor.setVisibility(View.VISIBLE);
                }
                else
                {
                    nameEditor.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                nameSpinner.setSelection(0);
            }
        });

        // Take the existing information in the "New Faction" part of the UI, build an actual
        // Faction object, and add it to the list.
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // The new faction;s name comes from the spinner, *unless* the spinner is at
                // position 0 ("Custom..."), in which case we take the name from the editor instead.
                String name;
                if(nameSpinner.getSelectedItemPosition() > 0)
                {
                    name = nameSpinner.getSelectedItem().toString();
                }
                else
                {
                    name = nameEditor.getText().toString();
                }

                // Create and add the new faction. This gives us the position at which the faction
                // was added to the list, which is important because...
                int insertPosition = factionList.add(new Faction(
                    name,
                    Integer.parseInt(strength.getText().toString()),
                    relationship.getSelectedItemPosition()));

                // ... we need to notify the adapter where the new item was inserted. And we'd also
                // like to scroll to that position to ensure it's visible.
                adapter.notifyItemInserted(insertPosition);
                rvLayout.scrollToPosition(insertPosition);
            }
        });

        // Set up the RecyclerView
        adapter = new FactionAdapter();
        rvLayout = new LinearLayoutManager(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(rvLayout);

        return view;
    }

    private class FactionViewHolder extends RecyclerView.ViewHolder
    {
        private Faction fac;
        private EditText name;
        private EditText strength;
        private Button delButton;
        private Spinner relationship;

        private TextWatcher tw;
        private AdapterView.OnItemSelectedListener relationshipListener;

        public FactionViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_entry, parent, false));

            // Get references to the UI elements in each list row.
            name = (EditText) itemView.findViewById(R.id.name);
            strength = (EditText) itemView.findViewById(R.id.strength);
            relationship = (Spinner) itemView.findViewById(R.id.relationship);
            delButton = (Button) itemView.findViewById(R.id.delButton);

            // 'tw' is an event handler that will be invoked whenever the name or the 'strength' of
            // a faction is edited. We apply the same event handler to both cases for simplicity.
            tw = new TextWatcher()
            {
                // We're required to override these methods, but we don't actually need to use
                // them.
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                // This is where we get notified that the text has actually been changed.
                @Override
                public void afterTextChanged(Editable editable)
                {
                    fac.setName(name.getText().toString());
                    fac.setStrength(Integer.parseInt(strength.getText().toString()));
                    factionList.edit(fac);
                }
            };

            // An event handler invoked when the faction changes between 'enemy', 'neutral' and
            // 'ally'.
            relationshipListener = new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    fac.setRelationship(position);
                    factionList.edit(fac);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView)
                {
                    relationship.setSelection(Faction.DEFAULT_RELATIONSHIP);
                }
            };

            // Event handler for the 'del' button -- for deleting a faction.
            delButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    factionList.remove(fac);
                    adapter.notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        public void bind(Faction fac)
        {
            this.fac = fac;

            // We must update the displayed name, strength and relationship. However, for each one
            // we have to temporarily disable the corresponding event handler, or else the event
            // handler would assume the *user* has edited the information.

            name.removeTextChangedListener(tw);
            name.setText(fac.getName());
            name.addTextChangedListener(tw);

            strength.removeTextChangedListener(tw);
            strength.setText(String.valueOf(fac.getStrength()));
            strength.addTextChangedListener(tw);

            relationship.setOnItemSelectedListener(null);
            relationship.setSelection(fac.getRelationship());
            relationship.setOnItemSelectedListener(relationshipListener);
        }
    }

    public class FactionAdapter extends RecyclerView.Adapter<FactionViewHolder>
    {
        @Override
        public FactionViewHolder onCreateViewHolder(ViewGroup container, int viewType)
        {
            return new FactionViewHolder(LayoutInflater.from(getActivity()), container);
        }

        @Override
        public void onBindViewHolder(FactionViewHolder vh, int position)
        {
            vh.bind(factionList.get(position));
        }

        @Override
        public int getItemCount()
        {
            return factionList.size();
        }
    }
}
