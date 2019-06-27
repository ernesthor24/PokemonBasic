package com.example.pokemonbasic;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokemonbasic.pojo.ConditionValue;
import com.example.pokemonbasic.pojo.Example;
import com.example.pokemonbasic.pojo.Method;
import com.example.pokemonbasic.pojo.PResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private EditText editText;
    private Button button;
    private TextView pokeInfoText;
    private ImageView pokeImage;

    private TextView pokeStat;
    private TextView pokeSkill;

    String[] pokemonTypes;
    String[] pokemonSkillsName;
    String[] pokemonSkillsMethod;
    int[] pokemonSkillLevel;
    String[] pokemonStats;
    String[] pokemonSprites;
    String[] pokemonLocations;

    private String locationEncounters = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        editText = findViewById(R.id.pokeEditText);
        button =  findViewById(R.id.pokeButton);
        pokeInfoText = findViewById(R.id.pokeInfo);
        pokeImage = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPokemonDetails();
            }
        });

    }


    private void fetchPokemonDetails() {

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        final PokemonAPI pokemonAPI = retrofit.create(PokemonAPI.class);
        Call<PResponse> call = pokemonAPI.getPokemonDetail(editText.getText().toString().toLowerCase());

        call.enqueue(new Callback<PResponse>() {
            @Override
            public void onResponse(Call<PResponse> call, Response<PResponse> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Pokemon Found", Toast.LENGTH_SHORT).show();

                    PResponse pResponse = response.body();

                    String pokeUrl = pResponse.getSprites().getFrontDefault();

                    String frontDef = pResponse.getSprites().getFrontDefault();
                    String backDef = pResponse.getSprites().getBackDefault();
                    String frontDefF;
                    String backDefF;
                    if(pResponse.getSprites().getFrontFemale() != null && pResponse.getSprites().getBackFemale() != null) {
                        frontDefF  = pResponse.getSprites().getFrontFemale().toString();
                        backDefF  = pResponse.getSprites().getBackFemale().toString();
                    } else {
                        frontDefF  = pResponse.getSprites().getFrontDefault();
                        backDefF  = pResponse.getSprites().getBackDefault();
                    }

                    pokemonSprites = new String[4];
                    pokemonSprites[0] = frontDef;
                    pokemonSprites[1] = backDef;
                    pokemonSprites[2] = frontDefF;
                    pokemonSprites[3] = backDefF;

                    int typeLen = pResponse.getTypes().size();
                    int skillLen  = pResponse.getMoves().size();
                    int statLen = pResponse.getStats().size();
                    //int versionSize;

                    pokemonTypes = new String[typeLen];
                    pokemonSkillsName = new String[skillLen];
                    //pokemonSkillLevel = new int[versionSize];
                    //pokemonSkillsMethod = new String[versionSize];
                    pokemonStats = new String[statLen];
                    locationEncounters = pResponse.getLocationAreaEncounters();

                    for(int i = 0; i < typeLen; i++) {
                        pokemonTypes[i] = pResponse.getTypes().get(i).getType().getName();
                    }

                    for(int i = 0; i < skillLen; i++) {
                        pokemonSkillsName[i] = pResponse.getMoves().get(i).getMove().getName();
                        int verLen = pResponse.getMoves().get(i).getVersionGroupDetails().size();
                        Log.d(TAG, String.valueOf(verLen));
                    }

                    for(int i = 0; i < statLen; i++) {
                        pokemonStats[i] = pResponse.getStats().get(i).getStat().getName() + ": " + pResponse.getStats().get(i).getBaseStat().toString();
                    }

                    switch (typeLen) {
                        case 1:
                            pokeInfoText.setText("Id Number: " + pResponse.getId() + "\n" +
                                    "Name: " + pResponse.getName() + "\n" +
                                    "Type 1: " + pokemonTypes[0] + "\n" +
                                    "Type 2: N.A" + "\n" +
                                    "Height: " + pResponse.getHeight() + "\n" +
                                    "Weight: " + pResponse.getWeight() + "\n");
                            break;

                        default:
                            pokeInfoText.setText("Id Number: " + pResponse.getId() + "\n" +
                                    "Name: " + pResponse.getName() + "\n" +
                                    "Type 1: " + pokemonTypes[0] + "\n" +
                                    "Type 2: " + pokemonTypes[1] + "\n" +
                                    "Height: " + pResponse.getHeight() + "\n" +
                                    "Weight: " + pResponse.getWeight() + "\n");
                            break;
                    }

                    Glide.with(MainActivity.this)
                            .load(pokeUrl)
                            .override(300,300)
                            .centerCrop()
                            .into(pokeImage);

                } else {
                    Toast.makeText(MainActivity.this, "No Pokemon Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PResponse> call, Throwable t) {
                Log.d(TAG, "failed code");
                call.cancel();
            }
        });

        /**
         * Locations retrofit
         */

        Call<List<Example>> locationCall = pokemonAPI.getPokemonLocation(editText.getText().toString().toLowerCase());

        locationCall.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Location Found", Toast.LENGTH_SHORT).show();
                    List<Example> example = response.body();
                    int pokemonList;
                    pokemonList = example.size();
                    if(pokemonList > 0) {
                        pokemonLocations = new String[pokemonList];
                        for(int i = 0; i < pokemonList; i++) {
                            pokemonLocations[i] = example.get(i).getLocationArea().getName();
                        }
                    } else {
                        String noLoc = "No Location/s Found";
                        pokemonLocations = new String[1];
                        pokemonLocations[0] = noLoc;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.d(TAG, "failed code", t);
                call.cancel();
            }
        });

    }

    public void openStats(View view) {

        Bundle statData = new Bundle();
        statData.putStringArray("stats", pokemonStats);
        statFragment statInfo = new statFragment();
        statInfo.setArguments(statData);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.infoFrame, statInfo);
        ft.commit();
    }

    public void openSkills(View view) {

        Bundle skillData = new Bundle();
        skillData.putStringArray("skillsName", pokemonSkillsName);
        //skillData.putStringArray("skillsMethod", pokemonSkillsMethod);
        //skillData.putIntArray("skillsLevel", pokemonSkillLevel);
        skillFragment skillInfo = new skillFragment();
        skillInfo.setArguments(skillData);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.infoFrame, skillInfo);
        ft.commit();
    }

    public void openSprites(View view) {
        Bundle spriteData = new Bundle();
        spriteData.putStringArray("sprites", pokemonSprites);
        spriteFragment spriteInfo = new spriteFragment();
        spriteInfo.setArguments(spriteData);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.infoFrame, spriteInfo);
        ft.commit();

    }


    public void openLocation(View view) {

        Log.d(TAG, String.valueOf(pokemonLocations.length));

        Bundle locData = new Bundle();
        locData.putStringArray("locations", pokemonLocations);
        locationFragment locationInfo = new locationFragment();
        locationInfo.setArguments(locData);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.infoFrame, locationInfo);
        ft.commit();
    }
}
