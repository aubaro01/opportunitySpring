package aubaro.opportunity.core.model.rel;

import aubaro.opportunity.Shared.core.models.StateModel;
import aubaro.opportunity.core.model.OpportunityModel;

public class OpportunityEquipmentModel {

    private Long id;
    private String name;
    private String description;

    private OpportunityBrandModel opportunityBrand;
    private StateModel State;
    private Long clientId;
}
