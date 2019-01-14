package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.datalayer.LocalMessageDb;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirMessage;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLocalMessageAction extends MimirLoggedInRequiredBaseAction {

    private LocalMessageDb localMessageDb;

    @Inject
    public FindLocalMessageAction(LocalMessageDb localMessageDb) {
        this.localMessageDb = localMessageDb;
    }

    @Override
    protected ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                                MimirSessionContext mimirSessionContext) {

        List<MimirMessage> messages = localMessageDb.getLocalMessages(mimirSessionContext);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("messages", messages);

        return ActionResult.builder()
                .resultMap(resultMap)
                .build();
    }

    @Override
    public List<String> handles() {
        return ImmutableList.of("fm", "findmessage");
    }
}
