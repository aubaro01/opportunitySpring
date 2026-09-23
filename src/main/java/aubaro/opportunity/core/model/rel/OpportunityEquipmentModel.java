package aubaro.opportunity.core.model.rel;

import aubaro.Shared.core.models.StateModel;

public class OpportunityEquipmentModel {

    private Long id;
    private String name;
    private String description;

    private OpportunityBrandModel opportunityBrand;
    private StateModel State;
    private Long clientId;
}
