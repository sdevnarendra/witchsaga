package com.geekseat.test.witchsaga.controller;

import com.geekseat.test.witchsaga.constant.APIConstant;
import com.geekseat.test.witchsaga.dto.KillDataResponseDTO;
import com.geekseat.test.witchsaga.dto.KillSubjectDTO;
import com.geekseat.test.witchsaga.service.WitchKillDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(APIConstant.WITCH_SAGA_CONTROLLER)
public class WitchKillDataController {

    private final WitchKillDataService witchKillDataService;

    public WitchKillDataController(WitchKillDataService witchKillDataService) {
        this.witchKillDataService = witchKillDataService;
    }

    @PostMapping(APIConstant.SOLVE_WITCH_PROBLEM)
    KillDataResponseDTO solveWitchProblem(@RequestBody List<KillSubjectDTO> request){
        return witchKillDataService.solveWitchProblem(request);
    }
}
