package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

import java.util.List;

public class ExitAction extends MimirBaseAction {

    @Override
    public List<String> handles() {
        return ImmutableList.of("exit", "quit", "q");
    }

    @Override
    public ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                             MimirSessionContext mimirSessionContext) {
        mimirUIElements.getOutputFacility().output("Good Bye");
        return ActionResult.builder()
                .terminate(true)
                .exitCode(0)
                .build();
    }
}
