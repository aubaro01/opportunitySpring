package aubaro.opportunity.adapter.controller;


import aubaro.opportunity.adapter.mapper.OpportunityMapper;
import aubaro.opportunity.core.port.in.OpportunityOperations;
import aubaro.opportunity.core.service.OpportunityService;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Description( "Opportunities API")
@RequestMapping("/api/v1/opportunities")

public class OpportunityController {

    private final OpportunityOperations opportunityOperations;

    private final OpportunityMapper opportunityMapper;


}
