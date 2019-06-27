package com.example.pokemonbasic;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class spriteFragment extends Fragment {

    private ImageView frontDef;
    private ImageView backDef;
    private ImageView frontDefF;
    private ImageView backDefF;

    String[] pokemonSprites;

    public spriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sprite, container, false);

        frontDef = view.findViewById(R.id.frontDefault);
        backDef = view.findViewById(R.id.backDefault);
        frontDefF = view.findViewById(R.id.frontDefaultFem);
        backDefF = view.findViewById(R.id.backDefaultFem);

        pokemonSprites = getArguments().getStringArray("sprites");

        Glide.with(spriteFragment.this)
                .load(pokemonSprites[0])
                .override(400,400)
                .centerCrop()
                .into(frontDef);

        Glide.with(spriteFragment.this)
                .load(pokemonSprites[1])
                .override(400,400)
                .centerCrop()
                .into(backDef);

        Glide.with(spriteFragment.this)
                .load(pokemonSprites[2])
                .override(400,400)
                .centerCrop()
                .into(frontDefF);

        Glide.with(spriteFragment.this)
                .load(pokemonSprites[3])
                .override(400,400)
                .centerCrop()
                .into(backDefF);

        return view;
    }

}
