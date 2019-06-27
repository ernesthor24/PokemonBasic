package com.example.pokemonbasic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class locationFragment extends Fragment {

    private TextView pokeLocation;
    String[] pokemonLocation;

    public locationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        pokeLocation = view.findViewById(R.id.pokeLocation);
        pokemonLocation = getArguments().getStringArray("locations");

        String location = "";
        for(int i = 0; i < pokemonLocation.length; i++) {
            location += pokemonLocation[i] + "\n";
        }

        pokeLocation.setText(location);
        pokeLocation.setVisibility(View.VISIBLE);

        return view;

    }
}
