package com.geekseat.test.witchsaga.dto;

import java.math.BigDecimal;
import java.util.List;

public class KillDataResponseDTO {
    private List<KillSubjectResultDTO> killDataList;
    private BigDecimal averageResult;
    private String message;

    public List<KillSubjectResultDTO> getKillDataList() {
        return killDataList;
    }

    public void setKillDataList(List<KillSubjectResultDTO> killDataList) {
        this.killDataList = killDataList;
    }

    public BigDecimal getAverageResult() {
        return averageResult;
    }

    public void setAverageResult(BigDecimal averageResult) {
        this.averageResult = averageResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
