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
public class statFragment extends Fragment {

    private TextView pokeStat;
    String[] pokemonStats;
    public statFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat, container, false);

        pokeStat = view.findViewById(R.id.pokeStat);
        pokemonStats = getArguments().getStringArray("stats");

        String stat = "";
        int j = 1;
        for(int i = 0; i < pokemonStats.length; i++) {
            stat += pokemonStats[pokemonStats.length-j] + "\n";
            j++;
        }

        pokeStat.setText(stat);
        pokeStat.setVisibility(View.VISIBLE);

        return view;
    }
}
