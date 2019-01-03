package com.leeharkness.mimir.awssupport;

import org.beryx.textio.TextTerminal;

public class CognitoStub {
    public boolean login(String userName, String password, TextTerminal<?> terminal) {
        // TODO:  Use Cognito to log this user in
        terminal.println("Logging user " + userName + " in via Cognito ");
        return true;
    }
}
