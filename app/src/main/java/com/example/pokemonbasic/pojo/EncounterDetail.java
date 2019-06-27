package com.example.pokemonbasic.pojo;

import java.lang.reflect.Method;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncounterDetail {

    @SerializedName("chance")
    @Expose
    private Integer chance;
    @SerializedName("condition_values")
    @Expose
    private List<ConditionValue> conditionValues = null;
    @SerializedName("max_level")
    @Expose
    private Integer maxLevel;
    @SerializedName("method")
    @Expose
    private Method method;
    @SerializedName("min_level")
    @Expose
    private Integer minLevel;

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

    public List<ConditionValue> getConditionValues() {
        return conditionValues;
    }

    public void setConditionValues(List<ConditionValue> conditionValues) {
        this.conditionValues = conditionValues;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

}