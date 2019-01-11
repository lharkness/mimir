package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.datalayer.MessageDao;
import com.leeharkness.mimir.mimirsupport.MimirCryptoFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirMessage;

import javax.inject.Inject;
import java.util.List;

public class SendMessageAction extends MimirLoggedInRequiredBaseAction {

    private MessageDao messageDao;
    private MimirCryptoFacility mimirCryptoFacility;

    @Inject
    public SendMessageAction(MessageDao messageDao, MimirCryptoFacility mimirCryptoFacility) {
        this.messageDao = messageDao;
        this.mimirCryptoFacility = mimirCryptoFacility;
    }

    @Override
    protected ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                                MimirSessionContext mimirSessionContext) {
        MimirMessage mimirMessage = MimirMessage.builder()
                .message(input[1])
                .fromUserName(mimirSessionContext.getUser().getUserName())
                .toUserName(input[2])
                .build();
        messageDao.sendMessage(mimirMessage, mimirSessionContext);

        return ActionResult.noOpResult();
    }

    @Override
    public List<String> handles() {
        return ImmutableList.of("s", "sm", "send");
    }
}
