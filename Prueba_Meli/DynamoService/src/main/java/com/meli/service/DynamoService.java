package com.meli.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.meli.Stats;

import java.util.List;

public interface DynamoService {
    void saveData(Stats stats);
    ScanResultPage<Stats> getData(String lastEvaluatedKey);
}
