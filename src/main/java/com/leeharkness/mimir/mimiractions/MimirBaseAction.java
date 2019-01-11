package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirAction;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MimirBaseAction implements MimirAction {
    @Override
    public ActionResult handle(String[] input, MimirUIElements mimirUIElements, MimirSessionContext mimirSessionContext) {

        Optional<ActionResult> preResult = preconditions(input, mimirSessionContext, mimirUIElements);

        if (preResult.isPresent()) {
            return preResult.get();
        }

        // Set the prompt
        String prompt = ">";
        if (mimirSessionContext.getUser() != null) {
            prompt = mimirSessionContext.getUser().getUserName() + ">";
        }

        mimirUIElements.setPrompt(prompt);
        ActionResult result = actionSpecificHandle(input, mimirUIElements, mimirSessionContext);

        Optional<ActionResult> postResult = postConditions(result, mimirSessionContext, mimirUIElements);

        return postResult.orElse(result);

    }

    @Override
    public ActionResult handle(String input, MimirUIElements mimirUIElements, MimirSessionContext mimirSessionContext) {
        List<String> pieces = new ArrayList<>();
        Matcher m = Pattern.compile("(\".*\"|[^\\s.]+)").matcher(input);
        while (m.find()){
            pieces.add(m.group(1).replaceAll("\"", ""));
        }

        String[] piecesArray = new String[pieces.size()];
        piecesArray = pieces.toArray(piecesArray);
        return  handle(piecesArray, mimirUIElements, mimirSessionContext);
    }

    protected Optional<ActionResult> preconditions(String[] input, MimirSessionContext mimirSessionContext,
                                                   MimirUIElements mimirUIElements) {return Optional.empty();}

    protected abstract ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                                         MimirSessionContext mimirSessionContext);

    protected Optional<ActionResult> postConditions(ActionResult actionResult, MimirSessionContext mimirSessionContext,
                                  MimirUIElements mimirUIElements) {return Optional.empty();}
}
