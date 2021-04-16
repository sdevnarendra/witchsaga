package com.geekseat.test.witchsaga.service;

import com.geekseat.test.witchsaga.dto.KillDataResponseDTO;
import com.geekseat.test.witchsaga.dto.KillSubjectDTO;
import com.geekseat.test.witchsaga.dto.KillSubjectResultDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class WitchKillDataService {

    public KillDataResponseDTO solveWitchProblem(List<KillSubjectDTO> request) {
        KillDataResponseDTO result = new KillDataResponseDTO();
        List<KillSubjectResultDTO> killDataList = new ArrayList<>();
        String message = "Success";

        Integer total = 0;
        for (KillSubjectDTO data : request) {
            KillSubjectResultDTO currentData = new KillSubjectResultDTO();
            BeanUtils.copyProperties(data, currentData);
            try {
                Integer currentRangeNumber = getRangeNumber(currentData);
                currentData.setKillCycleYear(currentRangeNumber);
                if (currentRangeNumber >= 0) {
                    calculateWitchKillPattern(currentData);
                    killDataList.add(currentData);
                    total += currentData.getVillagersKilledInThatYear();
                } else {
                    throw new IOException("Current (age of death - year of death) calculation is negative, calculation terminated.");
                }
            } catch (IOException e) {
                currentData.setVillagersKilledInThatYear(-1);
                currentData.setKillCycleYear(-1);
                currentData.setKillCyclePattern("-1");
                message = e.getLocalizedMessage();
                total = -1;
                killDataList.add(currentData);
                break;
            }
        }

        result.setMessage(message);
        result.setKillDataList(killDataList);
        result.setTotalSum(total);
        result.setNumberOfData(killDataList.size());
        if(total<0){
            result.setAverageResult(new BigDecimal(-1));
        } else {
            result.setAverageResult(killDataList.size()>0?getTotalAverage(total, killDataList.size()):new BigDecimal(0));
        }

        return result;
    }

    private Integer getRangeNumber(KillSubjectDTO data) throws IOException {
        if (data.getAgeOfDeath() < 0 || data.getYearOfDeath() < 0) {
            throw new IOException("Current age of death or year of death value is negative, calculation terminated.");
        }
        return data.getYearOfDeath() - data.getAgeOfDeath();
    }

    private void calculateWitchKillPattern(KillSubjectResultDTO dto) {
        Integer total = 0;
        Integer currValue = 0;
        Integer nextValue = 1;
        Integer killCycleYear = dto.getKillCycleYear();
        StringBuffer killPattern = new StringBuffer();
        for (Integer i = 1; i <= killCycleYear; ++i) {
            Integer sum = currValue + nextValue;
            currValue = nextValue;
            nextValue = sum;
            if(i==1){
                killPattern.append(currValue);
            } else {
                killPattern.append(" + "+currValue);
            }
            total +=currValue;
        }
        dto.setKillCyclePattern(killPattern.toString());
        dto.setVillagersKilledInThatYear(total);
    }

    private BigDecimal getTotalAverage(Integer numberTotal, Integer numberOfData){
        return new BigDecimal(numberTotal).divide(new BigDecimal(numberOfData));
    }
}
