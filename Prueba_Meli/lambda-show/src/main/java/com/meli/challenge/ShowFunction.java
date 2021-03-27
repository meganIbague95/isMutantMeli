package com.meli.challenge;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.lambda.runtime.Context;
import com.meli.Stats;
import com.meli.service.DynamoService;
import com.meli.service.DynamoServiceImpl;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowFunction implements RequestHandler<RequestShow, ResponseShow> {
    DynamoService service = new DynamoServiceImpl();

    @Override
    public ResponseShow handleRequest(RequestShow lastEvaluatedKey, Context context) {
        ResponseShow response = new ResponseShow();
        ScanResultPage<Stats> result = service.getData(lastEvaluatedKey.getLastEvaluatedKey());
        List<Stats> stats= result.getResults();
        response.setLastEvaluatedKey(result.getLastEvaluatedKey().get("id").getS());
        response.setStats(stats);
        return response;
    }
}
