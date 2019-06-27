package com.example.pokemonbasic.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VersionEncounterDetail {

    @SerializedName("encounter_details")
    @Expose
    private List<EncounterDetail> encounterDetails = null;
    @SerializedName("max_chance")
    @Expose
    private Integer maxChance;
    @SerializedName("version")
    @Expose
    private Version version;

    public List<EncounterDetail> getEncounterDetails() {
        return encounterDetails;
    }

    public void setEncounterDetails(List<EncounterDetail> encounterDetails) {
        this.encounterDetails = encounterDetails;
    }

    public Integer getMaxChance() {
        return maxChance;
    }

    public void setMaxChance(Integer maxChance) {
        this.maxChance = maxChance;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}