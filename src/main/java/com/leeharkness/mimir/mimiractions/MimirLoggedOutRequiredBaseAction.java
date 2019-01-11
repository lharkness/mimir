package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

import java.util.Optional;

public abstract class MimirLoggedOutRequiredBaseAction extends MimirBaseAction {

    @Override
    protected Optional<ActionResult> preconditions(String[] input, MimirSessionContext mimirSessionContext,
                                                   MimirUIElements mimirUIElements) {
        if (mimirSessionContext.getUser() != null) {
            mimirUIElements.getOutputFacility().output("Must logout first");
            return Optional.of(ActionResult.noOpResult());
        }
        return Optional.empty();
    }
}
