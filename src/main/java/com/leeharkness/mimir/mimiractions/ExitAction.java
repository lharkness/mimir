package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirAction;
import com.leeharkness.mimir.MimirUIElements;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

import java.util.List;

public class ExitAction implements MimirAction {

    @Override
    public List<String> handles() {
        return ImmutableList.of("exit", "quit");
    }

    @Override
    public ActionResult handle(String input, MimirUIElements mimirUIElements) {
        mimirUIElements.getOutputFacility().output("Good Bye");
        return ActionResult.builder()
                .terminate(true)
                .exitCode(0)
                .build();
    }
}
