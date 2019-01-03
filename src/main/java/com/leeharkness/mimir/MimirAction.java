package com.leeharkness.mimir;

import java.util.List;

public interface MimirAction {

    List<String> handles();

    ActionResult handle(String input, MimirUIElements mimirUIElements);
}
