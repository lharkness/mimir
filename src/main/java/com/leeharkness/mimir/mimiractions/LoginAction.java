package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirAction;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LoginAction implements MimirAction {

    public static final String NULL_TOKEN = "NULL_TOKEN";

    private CognitoStub cognitoStub;

    @Inject
    public LoginAction(CognitoStub cognitoStub) {
        this.cognitoStub = cognitoStub;
    }

    @Override
    public List<String> handles() {
        return Collections.singletonList("login");
    }

    @Override
    public ActionResult handle(String input, MimirUIElements mimirUIElements) {
        String[] parts = input.split(" ");
        String userName;
        if (parts.length > 1) {
            userName = parts[1];
        }
        else {
            userName = mimirUIElements.getInputFacility().promptForInputUsing("User Name>");
        }
        String password;
        if (parts.length > 2) {
            password = parts[2];
        }
        else {
            password = mimirUIElements.getInputFacility().promptForPasswordUsing("Password>");
        }

        Optional<String> loginResult = cognitoStub.login(userName, password, mimirUIElements.getOutputFacility());

        return ActionResult.builder()
                .prompt(userName + ">")
                .loggedIn(loginResult.isPresent())
                .backEndToken(loginResult.orElse(NULL_TOKEN))
                .build();
    }
}
