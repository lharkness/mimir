package com.leeharkness.mimir.datalayer;

import com.google.inject.Inject;
import com.leeharkness.mimir.awssupport.SNSStub;
import com.leeharkness.mimir.awssupport.SQSStub;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirMessage;

import java.util.List;

public class MessageDao {

    private SQSStub sqsStub;
    private SNSStub snsStub;

    @Inject
    public MessageDao(SQSStub sqsStub, SNSStub snsStub) {
        this.sqsStub = sqsStub;
        this.snsStub = snsStub;
    }

    public List<MimirMessage> retrieveMessages(MimirSessionContext mimirSessionContext) {

        return sqsStub.getMessagesFor(mimirSessionContext);

    }

    public boolean sendMessage(MimirMessage mimirMessage, MimirSessionContext mimirSessionContext) {
        snsStub.sendMessage(mimirMessage.getMessage(), mimirMessage.getMimirContact(), mimirSessionContext);
        return true;
    }
}
