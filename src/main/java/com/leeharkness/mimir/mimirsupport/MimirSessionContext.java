package com.leeharkness.mimir.mimirsupport;

import com.google.common.collect.ImmutableMap;
import com.leeharkness.mimir.model.MimirUser;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class MimirSessionContext {
    private MimirUser user;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, String> parameters = new HashMap<>();

    public void setParameter(String key, String value) {
        parameters.put(key, value);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public Map<String, String> getParameters() {
        return ImmutableMap.copyOf(parameters);
    }

    public void clear() {
        user = null;parameters = null;
    }
}
