package aubaro.opportunity.adapter.controller;


import aubaro.opportunity.Shared.core.models.PageModel;
import aubaro.opportunity.adapter.dto.request.createOpportunity;
import aubaro.opportunity.adapter.dto.response.OpportunityResponse;
import aubaro.opportunity.adapter.mapper.OpportunityMapper;
import aubaro.opportunity.core.model.OpportunityCommentModel;
import aubaro.opportunity.core.model.OpportunityModel;
import aubaro.opportunity.core.port.in.OpportunityOperations;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Description( "Opportunities API")
@RequestMapping("/api/v1/opportunities")

public class OpportunityController {

    private final OpportunityOperations opportunityOperations;
    private final OpportunityMapper opportunityMapper;


    @GetMapping()
    @Description("Get all opportunities")
    public List<OpportunityResponse> getAllOpportunities(@RequestParam(defaultValue = "0") int pageNo,
                                                         @RequestParam(defaultValue = "10") int pageSize,
                                                         @RequestParam(defaultValue = "startDate::asc") String sortBy,
                                                         @RequestParam(defaultValue = "") String filterBy) {

        log.debug("OpportunityController.getAllOpportunities :: Getting all opportunities with the parameters: pageNo: {}, pageSize: {}, sortBy: {}, filterBy: {}", pageNo, pageSize, sortBy, filterBy);

        PageModel<OpportunityModel> opportunities = opportunityOperations.getAllOpportunities(pageNo, pageSize, sortBy, filterBy);

        log.debug("OpportunityController.getAllOpportunities :: Opportunities found: {}", opportunities);

        //return opportunityMapper.modelToResponse(opportunities);

        return null;
    }

    @GetMapping("/id")
    @Description("Get opportunity by id")
    public OpportunityResponse getOpportunityById(Long id) {

        log.debug("OpportunityController.getOpportunityById :: getting opportunity with the id:{}", id);

        OpportunityModel opportunity = opportunityOperations.getOpportunityById(id);

        log.debug("OpportunityController.getOpportunityById :: Opportunity found: {}", opportunity);

        return opportunityMapper.modelToResponse(opportunity);
    }

    @PostMapping("/create")
    @Description("Create a new opportunity")
    public OpportunityResponse createOpportunity(@RequestBody createOpportunity createRequest) {

        log.debug("OpportunityController.createOpportunity :: creating opportunity: {}", createRequest);

        OpportunityModel opportunity = OpportunityModel.builder()
                .opportunityName(createRequest.getName())
                .opportunityReference(createRequest.getReference())
                .opportunityAmount(createRequest.getOpportunityAmount())
                .opportunityEstimatedAmount(createRequest.getOpportunityEstimatedAmount())
                .opportunityCurrency(createRequest.getOpportunityCurrency())
                .opportunityStartDate(createRequest.getOpportunityStartDate())
                .opportunityEndDate(createRequest.getOpportunityEndDate())
                .opportunityComments(createRequest.getOpportunityComments() != null && !createRequest.getOpportunityComments().isBlank() ? List.of(OpportunityCommentModel.builder().comment(createRequest.getOpportunityComments()).build()) : null)
                .build();

        log.debug("OpportunityController.createOpportunity :: opportunity created: {}", opportunity);

        opportunityOperations.saveOpportunity(opportunity);

        log.debug("OpportunityController.createOpportunity :: opportunity saved: {}", opportunity);

        return OpportunityResponse.builder()
                .id(opportunity.getId())
                .build();
    }

    @PutMapping("/update/{opportunityId}")
    @Description("Update an existing opportunity")
    public OpportunityResponse updateOpportunity(@PathVariable("opportunityId") Long opportunityId,
                                                 @RequestBody OpportunityModel opportunity) {
        return null;
    }

    @DeleteMapping("/delete/{opportunityId}")
    @Description("Delete an opportunity by id")
    public Boolean deleteOpportunity(@PathVariable("opportunityId") Long opportunityId) {

        log.debug("OpportunityController.deleteOpportunity :: deleting opportunity with id: {}", opportunityId);

       Boolean deleteOpportunity =  opportunityOperations.deleteOpportunity(opportunityId);

       if(!deleteOpportunity) {
           log.error("OpportunityController.deleteOpportunity :: error deleting opportunity");
           throw new RuntimeException("OpportunityController.deleteOpportunity :: error deleting opportunity");
       }

        log.debug("OpportunityController.deleteOpportunity :: opportunity deleted");
        return deleteOpportunity;

    }

}
