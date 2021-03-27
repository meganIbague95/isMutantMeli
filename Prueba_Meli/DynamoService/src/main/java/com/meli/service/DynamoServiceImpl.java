package com.meli.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.meli.Stats;

import java.util.HashMap;
import java.util.Objects;

public class DynamoServiceImpl implements DynamoService{
    private DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());
    @Override
    public void saveData(Stats stats) {
        try {
            mapper.save(stats);
        }catch (Exception e){
            throw new CustomExceptions("Error al guardar el stats",e);
        }

    }

    @Override
    public ScanResultPage<Stats> getData(String lastEvaluatedKey) {
        DynamoDBScanExpression expression=new DynamoDBScanExpression();
        expression.setLimit(15);
        expression.setProjectionExpression("countMutantDna,countHumanDna,ratio");
        if(!Objects.isNull(lastEvaluatedKey) && !lastEvaluatedKey.isEmpty()){
            AttributeValue key = new AttributeValue();
            key.setS(lastEvaluatedKey);
            HashMap<String,AttributeValue> map = new HashMap<>();
            map.put("id",key);
            expression.setExclusiveStartKey(map);
        }
        return mapper.scanPage(Stats.class,expression);
    }
}
