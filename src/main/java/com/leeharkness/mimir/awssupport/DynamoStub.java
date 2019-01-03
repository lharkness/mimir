package com.leeharkness.mimir.awssupport;

import com.leeharkness.mimir.mimirsupport.MimirKey;

public class DynamoStub {
    public void storeKey(MimirKey key, String userName) {
        System.out.println("*** Storing key");
    }
}
