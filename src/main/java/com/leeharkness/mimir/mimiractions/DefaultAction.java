package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

import java.util.Collections;
import java.util.List;

public class DefaultAction extends MimirBaseAction {
    @Override
    public List<String> handles() {
        return Collections.emptyList();
    }

    @Override
    public ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                             MimirSessionContext mimirSessionContext) {
        mimirUIElements.getOutputFacility().output("Unknown Command");
        return ActionResult.builder().terminate(false).build();
    }
}
