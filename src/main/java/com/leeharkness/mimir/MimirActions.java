package com.leeharkness.mimir;

import com.google.inject.name.Named;
import com.leeharkness.mimir.mimiractions.DefaultAction;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.Set;

public class MimirActions {

    Set<MimirAction> actions;

    @Inject
    public MimirActions(@Named("ActionSet")Set<MimirAction> actions) {
        this.actions = actions;
    }

    MimirAction from(String input) {

        // TODO: fragile
        String key = input.split(" ")[0];

        MimirAction retAction = new DefaultAction();

        for (MimirAction action : actions) {
            if (action.handles().stream().anyMatch(key::equalsIgnoreCase)) {
                return action;
            }
        }

        return retAction;
    }
}
