package com.meli.challenge;

import com.meli.Stats;

import java.util.List;

public class ResponseShow {

    private List<Stats> stats;
    private String lastEvaluatedKey;

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public String getLastEvaluatedKey() {
        return lastEvaluatedKey;
    }

    public void setLastEvaluatedKey(String lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
    }
}
