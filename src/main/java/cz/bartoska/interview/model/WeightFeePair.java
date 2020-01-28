package cz.bartoska.interview.model;

import java.math.BigDecimal;

/**
 * Entity containing weight and fee.
 */
public class WeightFeePair {
    private final BigDecimal weight;
    private final BigDecimal fee;

    public WeightFeePair(BigDecimal weight, BigDecimal fee) {
        this.weight = weight;
        this.fee = fee;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getFee() {
        return fee;
    }
}
