package aubaro.opportunity.core.service;

import aubaro.opportunity.Shared.core.models.PageModel;
import aubaro.opportunity.core.model.OpportunityModel;
import aubaro.opportunity.core.port.in.OpportunityOperations;
import aubaro.opportunity.core.port.out.OpportunityPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j

public class OpportunityService implements OpportunityOperations {

    private final OpportunityPersistence opportunityPersistence;

    private final static Long MAX_LENGTH = 200L;
    private final static Long MIN_LENGTH = 10L;


    @Override
    public PageModel<OpportunityModel> getAllOpportunities() {
        log.debug("OpportunityService.getAllOpportunities");
        return opportunityPersistence.getAllOpportunities();
    }

    @Override
    public OpportunityModel getOpportunityById(Long id) {
        log.debug("OpportunityService.getOpportunityById: id={}", id);
        return null;
    }

    @Override
    public OpportunityModel saveOpportunity(OpportunityModel opportunity) {
        log.debug("OpportunityService.saveOpportunity: opportunity={}", opportunity);
        return null;
    }
}
