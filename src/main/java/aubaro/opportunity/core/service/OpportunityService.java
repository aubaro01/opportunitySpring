package aubaro.opportunity.core.service;

import aubaro.opportunity.Shared.core.models.PageModel;
import aubaro.opportunity.core.message.MsgOpportunityErrors;
import aubaro.opportunity.core.model.OpportunityModel;
import aubaro.opportunity.core.port.in.OpportunityOperations;
import aubaro.opportunity.core.port.out.OpportunityPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.debug("OpportunityService.getOpportunityById :: finding opportunity id: {}", id);
        return opportunityPersistence.getOpportunityById(id);
    }

    @Override
    public OpportunityModel saveOpportunity(OpportunityModel opportunity) {
        log.debug("OpportunityService.saveOpportunity :: saving opportunity: {}", opportunity);

        boolean isValid = validateCreatingOpportunity(opportunity);

        if(!isValid) {
            log.debug("OpportunityService.saveOpportunity :: opportunity is not valid");
            return null;
        }

        log.debug("OpportunityService.saveOpportunity :: opportunity is valid");

        return opportunityPersistence.saveOpportunity(opportunity);
    }

    @Override
    public boolean deleteOpportunity(Long id) {

        log.debug("OpportunityService.deleteOpportunity: id={}", id);

        OpportunityModel existsOpportunity = opportunityPersistence.getOpportunityById(id);

        if(existsOpportunity == null) {
            log.error("OpportunityService.deleteOpportunity :: Opportunity not found");
            throw new IllegalArgumentException(String.valueOf(MsgOpportunityErrors.OPT_0001));
        }

        log.debug("OpportunityService.deleteOpportunity :: Opportunity found");

        // delete comments
        if(!opportunityPersistence.deleteOpportunityComments(id)){
            log.error("OpportunityService.deleteOpportunity :: error deleting comments");
            throw new RuntimeException("OpportunityService.deleteOpportunity :: error deleting comments");
        }

        // delete movements
        if(!opportunityPersistence.deleteOpportunityMovements(id)){
            log.error("OpportunityService.deleteOpportunity :: error deleting movements");
            throw new RuntimeException("OpportunityService.deleteOpportunity :: error deleting movements");
        }

       return  opportunityPersistence.deleteOpportunity(id);

    }

    private boolean validateCreatingOpportunity(OpportunityModel opportunity) {
        return true;
    }

}
