package aubaro.opportunity.core.service;

import aubaro.Shared.core.models.PageModel;
import aubaro.opportunity.core.message.MsgOpportunityErrors;
import aubaro.opportunity.core.model.OpportunityModel;
import aubaro.opportunity.core.port.in.OpportunityOperations;
import aubaro.opportunity.core.port.out.OpportunityPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j

public class OpportunityService implements OpportunityOperations {

    private final OpportunityPersistence opportunityPersistence;

    private final static Long SUBMITTED_STATE = 179L;
    private final static Long EXTEND_STATE = 186L;
    private final static Long MAX_LENGTH = 255L;
    private final static Long MIN_LENGTH = 5L;
    private final static Long MAX_NAME_LENGTH = 200L;

    @Override
    public PageModel<OpportunityModel> getAllOpportunities(int pageNo, int pageSize, String sortBy, String FilterBy) {
        log.debug("OpportunityService.getAllOpportunities :: getting all opportunities with the parameters: pageNo: {}, pageSize: {}, sortBy: {}, FilterBy: {}", pageNo, pageSize, sortBy, FilterBy);
        return opportunityPersistence.getAllOpportunities(pageNo, pageSize, sortBy, FilterBy);
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

    // This validate max and min length and other stuff
    private boolean validateCreatingOpportunity(OpportunityModel opportunity) {

        log.debug("OpportunityService.validateCreatingOpportunity :: validating opportunity: {}", opportunity);

        if (opportunity != null){
            if(opportunity.getOpportunityName().length() < MIN_LENGTH || opportunity.getOpportunityName().length() > MAX_LENGTH) {
                log.error("OpportunityService.validateCreatingOpportunity :: Opportunity name is not valid");
                return false;
            }

            if(opportunity.getOpportunityReference().length() < MIN_LENGTH || opportunity.getOpportunityReference().length() > MAX_LENGTH) {
                log.error("OpportunityService.validateCreatingOpportunity :: Opportunity reference is not valid");

            }

            if(opportunity.getOpportunityStartDate() != null && opportunity.getOpportunityEndDate() != null) {
                if(opportunity.getOpportunityEndDate().isBefore(opportunity.getOpportunityStartDate())) {
                    log.debug("OpportunitiesService.isOpportunityModelValid :: End date is before start date ::");
                    //throw new IllegalArgumentException(MsgOpportunityErrors.OPT_000015);
                }
            }

            validateMonetaryValue(opportunity.getOpportunityAmount());
            validateMonetaryValue(opportunity.getOpportunityEstimatedAmount());
            validateMonetaryValue(opportunity.getOpportunityFinishedAmount());

            if(opportunity.getOpportunityComments() != null){
                for (var comment : opportunity.getOpportunityComments()) {
                    if(comment.getComment().length() < MIN_LENGTH || comment.getComment().length() > MAX_LENGTH) {
                        log.error("OpportunityService.validateCreatingOpportunity :: Opportunity comment is not valid");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /// Validates that a monetary value is positive and has at most 2 decimal places and 18 digits in total.
    private void validateMonetaryValue(BigDecimal value) throws IllegalArgumentException {
        if (value == null) {
            return;
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            log.debug("OpportunityService.validateMonetaryValue :: Monetary value is not positive ::");
            //throw new BusinessException(MsgOpportunitiesErrors.OPT_000014);
        }

        if (value.scale() > 2) {
            log.debug("OpportunityService.validateMonetaryValue :: Monetary value has more than 2 decimal places ::");
            //throw new BusinessException(MsgOpportunitiesErrors.OPT_000018);
        }

        if (value.precision() > 18) {
            log.debug("OpportunityService.validateMonetaryValue :: Monetary value has more than 18 digits ::");
            //throw new BusinessException(MsgOpportunitiesErrors.OPT_000019);
        }
    }

}
