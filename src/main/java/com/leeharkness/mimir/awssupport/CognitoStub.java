package com.leeharkness.mimir.awssupport;

import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;

import java.util.Optional;
import java.util.UUID;

public class CognitoStub {
    public Optional<String> login(String userName, String password, MimirOutputFacility terminal) {
        // TODO:  Use Cognito to log this user in
        terminal.output("Logging user " + userName + " in via Cognito ");
        return Optional.of(UUID.randomUUID().toString());
    }

    public void logout(String userName) {
        // TODO: Use Cognito to log this user out
    }
}
