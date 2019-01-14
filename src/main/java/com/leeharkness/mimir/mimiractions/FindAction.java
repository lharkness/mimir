package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

import javax.inject.Inject;
import java.util.List;

public class FindAction extends MimirLoggedInRequiredBaseAction{

    private FindLocalMessageAction findLocalMessageAction;
    private FindContactAction findContactAction;

    @Inject
    public FindAction(FindContactAction findContactAction, FindLocalMessageAction findLocalMessageAction) {
        this.findContactAction = findContactAction;
        this.findLocalMessageAction = findLocalMessageAction;
    }

    @Override
    protected ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                                MimirSessionContext mimirSessionContext) {
        String searchType = input[1];
        String inputString = input[2];
        switch(searchType) {
            case "contact":
            case "c":
                inputString = "fc " + inputString;
                return findContactAction.handle(inputString, mimirUIElements, mimirSessionContext);
            case "message":
            case "m":
                inputString = "fm " + inputString;
                return findLocalMessageAction.handle(inputString, mimirUIElements, mimirSessionContext);
            default:
                mimirUIElements.getOutputFacility().output("Unknown search type: " + searchType);
                return ActionResult.noOpResult();
        }
    }

    @Override
    public List<String> handles() {
        return ImmutableList.of("find", "f");
    }
}
