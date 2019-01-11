package com.leeharkness.mimir.awssupport;

import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

public class CloudFormationStub {
    public String newUserSetup(MimirSessionContext mimirSessionContext) {
        System.out.println("Setting up SNS Filtering and SQS queues for new user");
        return mimirSessionContext.getUser().getUserName();
    }
}
