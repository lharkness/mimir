package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;
import com.leeharkness.mimir.awssupport.DynamoStub;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirUser;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LoginAction extends MimirLoggedOutRequiredBaseAction {

    private CognitoStub cognitoStub;
    private DynamoStub dynamoStub;

    @Inject
    public LoginAction(CognitoStub cognitoStub, DynamoStub dynamoStub) {
        this.cognitoStub = cognitoStub;
        this.dynamoStub = dynamoStub;
    }

    @Override
    public List<String> handles() {
        return Collections.singletonList("login");
    }

    @Override
    public ActionResult actionSpecificHandle(String[] parts, MimirUIElements mimirUIElements,
                                             MimirSessionContext mimirSessionContext) {

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

        loginResult.ifPresent(s -> {
                    mimirSessionContext.setUser(
                            MimirUser.builder()
                                    .backendToken(s)
                                    .userName(userName)
                                    .mimirKey(dynamoStub.retrieveKey(userName))
                                    .build());
                    mimirUIElements.setPrompt(userName + ">");
                }
        );

        return ActionResult.builder()
                .loggedIn(loginResult.isPresent())
                .build();
    }
}
