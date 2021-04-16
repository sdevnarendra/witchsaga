package com.geekseat.test.witchsaga.dto;

public class KillSubjectResultDTO extends KillSubjectDTO {
    private Integer killCycleYear;
    private String killCyclePattern = "0";
    private Integer villagersKilledInThatYear;

    public Integer getKillCycleYear() {
        return killCycleYear;
    }

    public void setKillCycleYear(Integer killCycleYear) {
        this.killCycleYear = killCycleYear;
    }

    public String getKillCyclePattern() {
        return killCyclePattern;
    }

    public void setKillCyclePattern(String killCyclePattern) {
        this.killCyclePattern = killCyclePattern;
    }

    public Integer getVillagersKilledInThatYear() {
        return villagersKilledInThatYear;
    }

    public void setVillagersKilledInThatYear(Integer villagersKilledInThatYear) {
        this.villagersKilledInThatYear = villagersKilledInThatYear;
    }
}
