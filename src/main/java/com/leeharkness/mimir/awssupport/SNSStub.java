package com.leeharkness.mimir.awssupport;

import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirContact;
import com.leeharkness.mimir.model.MimirMessage;

import java.time.Instant;

public class SNSStub {
    public void sendMessage(String messageText, MimirContact mimirContact, MimirSessionContext mimirSessionContext) {
        System.out.println("*** sending message: " + messageText + " to " + mimirContact.getUserName());
        MimirMessage message = MimirMessage.builder()
                .fromUserName(mimirSessionContext.getUser().getUserName())
                .sentInstant(Instant.now())
                .message(messageText)
                .mimirContact(mimirContact)
                .build();
    }
}
