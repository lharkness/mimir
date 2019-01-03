package com.leeharkness.mimir;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the result of a user interaction
 */
@Data
@Builder
public class ActionResult {

    private boolean loggedIn;
    private String prompt;
    private int exitCode;
    private boolean terminate;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, String> resultMap = new HashMap<>();

    /**
     * To handle Generic return values
     * @param resultKey The key to the
     * @return the value associated with this key, null if not present
     */
    public String getResult(String resultKey) {
        return null;
    }
}
