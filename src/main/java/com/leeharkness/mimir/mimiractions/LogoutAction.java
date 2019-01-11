package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

import java.util.List;

public class LogoutAction extends MimirLoggedInRequiredBaseAction {

    private CognitoStub cognitoStub;

    @Inject
    public LogoutAction(CognitoStub cognitoStub) {
        this.cognitoStub =cognitoStub;
    }

    @Override
    public List<String> handles() {
        return ImmutableList.of("logout", "logoff");
    }

    @Override
    public ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                             MimirSessionContext mimirSessionContext) {
        cognitoStub.logout(input[1]);
        mimirSessionContext.clear();
        mimirUIElements.setPrompt(">");
        return ActionResult.builder().loggedIn(false).build();
    }
}
