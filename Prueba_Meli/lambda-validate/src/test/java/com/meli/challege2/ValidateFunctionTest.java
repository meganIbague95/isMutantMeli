package com.meli.challege2;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.meli.challenge2.Request;
import com.meli.challenge2.ValidateFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateFunctionTest {
    Context context = new Context() {
        @Override
        public String getAwsRequestId() {
            return "523452352";
        }

        @Override
        public String getLogGroupName() {
            return null;
        }

        @Override
        public String getLogStreamName() {
            return null;
        }

        @Override
        public String getFunctionName() {
            return "validate-prospect";
        }

        @Override
        public String getFunctionVersion() {
            return null;
        }

        @Override
        public String getInvokedFunctionArn() {
            return null;
        }

        @Override
        public CognitoIdentity getIdentity() {
            return null;
        }

        @Override
        public ClientContext getClientContext() {
            return null;
        }

        @Override
        public int getRemainingTimeInMillis() {
            return 0;
        }

        @Override
        public int getMemoryLimitInMB() {
            return 0;
        }

        @Override
        public LambdaLogger getLogger() {
            return null;
        }
    };
    ValidateFunction validator = new ValidateFunction();

    @Test
    public void run(){
        Request r= new Request();
        String[] array = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        r.setInput(array);
        Boolean b =validator.handleRequest(r,context);
        assertTrue(b);
    }
    @Test
    public void runFail(){
        Boolean b=false;
        try {
        Request r= new Request();
        String[] array = { "GTGCTA", "CAGTGC", "TTATGT", "AGAAGG", "GCCCTA", "TCACTG" };
        r.setInput(array);
         b =validator.handleRequest(r,context);
            fail("No ha saltado la excepción");
        } catch (Exception e) {
            assertFalse(b);
        }
    }


    @Test
    public void runFailLetter(){
        Boolean b=false;
        try {
            Request r= new Request();
            String[] array = { "GTGJTA", "CAGTGC", "TTATGT", "AGAAGG", "GCCCTA", "TCACTG" };
            r.setInput(array);
            b =validator.handleRequest(r,context);
            fail("No ha saltado la excepción");
        } catch (Exception e) {
            assertFalse(b);
        }
    }
}
