package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.SQSStub;
import com.leeharkness.mimir.datalayer.MessageDao;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetrieveMessagesAction extends MimirLoggedInRequiredBaseAction {

    private MessageDao messageDao;

    @Inject
    public RetrieveMessagesAction(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    protected ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                                MimirSessionContext mimirSessionContext) {
        List<MimirMessage> messages = messageDao.retrieveMessages(mimirSessionContext);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("messages", messages);
        ActionResult result = ActionResult.builder()
                .terminate(false)
                .resultMap(resultMap)
                .build();
        // DEBUG
        for (MimirMessage message : messages) {
            mimirUIElements.getOutputFacility().output(message.toString());
        }
        return result;
    }

    @Override
    public List<String> handles() {
        return ImmutableList.of("retrieve", "r", "list", "l");
    }

}
