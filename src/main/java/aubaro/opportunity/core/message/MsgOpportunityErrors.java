package aubaro.opportunity.core.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public enum MsgOpportunityErrors implements Serializable {

    OPT_0001("0001", "Opportunity not found"),
    OPT_0002("0002", "Error saving Opportunity"),
    OPT_0003("0003", "Error deleting Opportunity");

    private final String code;
    private final String message;

    @Override
    public String toString() {
        return this.code;
    }

}
