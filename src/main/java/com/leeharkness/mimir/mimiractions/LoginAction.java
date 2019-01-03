package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirAction;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class LoginAction implements MimirAction {

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
            userName = mimirUIElements.getTextIO().newStringInputReader().read("User Name>");
        }
        String password;
        if (parts.length > 2) {
            password = parts[2];
        }
        else {
            password = mimirUIElements.getTextIO().newStringInputReader().withInputMasking(true).read("Password>");
        }

        boolean loginResult = cognitoStub.login(userName, password, mimirUIElements.getTextTerminal());

        return ActionResult.builder()
                .prompt(userName + ">")
                .loggedIn(loginResult)
                .build();
    }
}
