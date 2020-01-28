package cz.bartoska.interview.model;

import java.math.BigDecimal;

/**
 * Entity containing weight and post code.
 */
public class WeightPostCodePair {
    private String postCode;
    private BigDecimal weight;

    public WeightPostCodePair(String postCode, BigDecimal weight) {
        this.postCode = postCode;
        this.weight = weight;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
