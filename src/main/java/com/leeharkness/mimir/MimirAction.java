package com.leeharkness.mimir;

import com.leeharkness.mimir.mimirsupport.MimirSessionContext;

import java.util.List;

public interface MimirAction {

    List<String> handles();

    ActionResult handle(String[] inputPieces, MimirUIElements mimirUIElements, MimirSessionContext mimirSessionContext);

    ActionResult handle(String input, MimirUIElements mimirUIElements, MimirSessionContext mimirSessionContext);
}
