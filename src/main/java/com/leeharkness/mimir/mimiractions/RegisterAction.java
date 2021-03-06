package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CloudFormationStub;
import com.leeharkness.mimir.awssupport.DynamoStub;
import com.leeharkness.mimir.mimirsupport.MimirKey;
import com.leeharkness.mimir.mimirsupport.MimirKeyGenerator;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.mimirsupport.PasswordChecker;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class RegisterAction extends MimirLoggedOutRequiredBaseAction {

    private MimirKeyGenerator keyGenerator;
    private DynamoStub dynamoStub;
    private CloudFormationStub cloudFormationStub;
    private LoginAction loginAction;
    private PasswordChecker passwordChecker;

    @Inject
    public RegisterAction(MimirKeyGenerator mimirKeyGenerator,
                          DynamoStub dynamoStub, LoginAction loginAction,
                          PasswordChecker passwordChecker, CloudFormationStub cloudFormationStub) {
        this.keyGenerator = mimirKeyGenerator;
        this.dynamoStub = dynamoStub;
        this.loginAction = loginAction;
        this.passwordChecker = passwordChecker;
        this.cloudFormationStub = cloudFormationStub;
    }

    @Override
    public List<String> handles() {
        return Collections.singletonList("register");
    }

    @Override
    public ActionResult actionSpecificHandle(String parts[], MimirUIElements mimirUIElements,
                               MimirSessionContext mimirSessionContext) {

        String userName;
        if (parts.length > 1) {
            userName = parts[1];
        }
        else {
            userName = mimirUIElements.getInputFacility().promptForInputUsing("User Name>");
        }
        String password = mimirUIElements.getInputFacility().promptForPasswordUsing("Password>");

        while (!passwordChecker.valid(password)) {
            password = mimirUIElements.getInputFacility().promptForPasswordUsing("Password>");
        }
        String passwordConfirm =mimirUIElements.getInputFacility().promptForPasswordUsing("Password (Confirm)>");
        while (!password.equals(passwordConfirm)) {
            mimirUIElements.getOutputFacility().output("Passwords don't match");
            passwordConfirm = mimirUIElements.getInputFacility().promptForPasswordUsing("Password (Confirm)>");
        }
        String email = mimirUIElements.getInputFacility().promptForInputUsing("email: ");

        // Setup queues
        cloudFormationStub.newUserSetup(mimirSessionContext);
        // generate public/private key
        MimirKey key = keyGenerator.createKeyPairFor(userName, password);
        // send things off
        dynamoStub.storeKey(key, userName);
        // log the user in
        String loginInput = "login " + userName + " " + password;
        return loginAction.handle(loginInput, mimirUIElements, mimirSessionContext);
    }
}
