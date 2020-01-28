package cz.bartoska.interview.repository;

import cz.bartoska.interview.model.WeightFeePair;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository containing Fees.
 */
@Repository
public class FeeRepository {

    private Map<BigDecimal, WeightFeePair> fees = new HashMap<>();

    private synchronized Map<BigDecimal, WeightFeePair> getFees(){
        return fees;
    }

    /**
     * Adds fee - weight pair to the repository.
     * @param weightFeePair pair to be added.
     */
    public void addFee(WeightFeePair weightFeePair) {
        getFees().put(weightFeePair.getWeight(), weightFeePair);
    }

    /**
     * Returns weight-fee pairs sorted by weight
     */
    public List<WeightFeePair> getSortedFees() {
        Comparator<WeightFeePair> weightFeePairComparator = Comparator.comparing(WeightFeePair::getWeight).reversed();
        return getFees().values()
                .stream()
                .sorted(weightFeePairComparator)
                .collect(Collectors.toList());
    }
}
