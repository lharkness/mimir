package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirAction;
import com.leeharkness.mimir.MimirUIElements;

import java.util.Collections;
import java.util.List;

public class DefaultAction implements MimirAction {
    @Override
    public List<String> handles() {
        return Collections.emptyList();
    }

    @Override
    public ActionResult handle(String input, MimirUIElements mimirUIElements) {
        mimirUIElements.getOutputFacility().output("Unknown Command");
        return ActionResult.builder().terminate(false).build();
    }
}
