package cz.bartoska.interview.repository;

import cz.bartoska.interview.model.WeightPostCodePair;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository containing post code - weights.
 */
@Repository
public class PostcodeRepository {

    private Map<String, BigDecimal> storage = new HashMap<>();

    private synchronized Map<String, BigDecimal> getStorage(){
        return storage;
    }

    /**
     * Adds new weight to the post code in the repository.
     * @param weightPostCodePair pair to be added.
     */
    public void addWeight(WeightPostCodePair weightPostCodePair) {
        BigDecimal oldWeight = getStorage().get(weightPostCodePair.getPostCode());
        if (oldWeight == null) {
            getStorage().put(weightPostCodePair.getPostCode(), weightPostCodePair.getWeight());
            return;
        }
        getStorage().put(weightPostCodePair.getPostCode(), oldWeight.add(weightPostCodePair.getWeight()));
    }

    /**
     * Returns post code weight pairs sorted by weight.
     */
    public List<WeightPostCodePair> getSortedRecords() {
        return getStorage().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> new WeightPostCodePair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
