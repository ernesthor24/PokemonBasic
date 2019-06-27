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
public class skillFragment extends Fragment {

    private TextView pokeSkill;
    String [] pokemonSkillsName;
    String [] pokemonSkillMethod;
    int [] pokemonSkillLevel;

    public skillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill, container, false);
        pokeSkill = view.findViewById(R.id.pokeSkill);

        pokemonSkillsName = getArguments().getStringArray("skillsName");
        //pokemonSkillMethod = getArguments().getStringArray("skillsMethod");
        //pokemonSkillLevel = getArguments().getIntArray("skillsLevel");

        String skill = "";
        for(int i = 0; i < pokemonSkillsName.length; i++) {
            skill += pokemonSkillsName[i] + "\n";
            //skill += pokemonSkillsName[i] + " - " + pokemonSkillLevel[i] + " - " + pokemonSkillMethod[i] + "\n";
        }
        pokeSkill.setText(skill);
        pokeSkill.setVisibility(View.VISIBLE);

        return view;
    }

}
