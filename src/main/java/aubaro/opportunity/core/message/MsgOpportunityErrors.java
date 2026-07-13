package aubaro.opportunity.core.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public enum MsgOpportunityErrors implements Serializable {

    OPT_0001("0001", "Opportunity not found");

    private final String code;
    private final String message;

    @Override
    public String toString() {
        return this.code;
    }

}
