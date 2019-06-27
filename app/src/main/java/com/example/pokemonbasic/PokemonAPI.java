package com.example.pokemonbasic;

import com.example.pokemonbasic.pojo.ConditionValue;
import com.example.pokemonbasic.pojo.Example;
import com.example.pokemonbasic.pojo.Method;
import com.example.pokemonbasic.pojo.PResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PokemonAPI {
    //post
    @GET("pokemon/{name}")
    Call<PResponse> getPokemonDetail(@Path("name") String pokeName);
    //you can add more path same as query

    @GET("pokemon/{name}/encounters")
    Call<List<Example>> getPokemonLocation(@Path("name") String pokeName);

    //query
    @GET("pokemon")
    Call<PResponse> getPokemonDetailQuery(
            @Query("name") String pokeName,
            //adding multiple query
            @Query("type") String pokeType );

    //query with random parameters then put the Map code in the java classs
    @GET("pokemon")
    Call<PResponse> getPokemonDetailQueryParameters(@QueryMap Map<String, String> parameters);
}
