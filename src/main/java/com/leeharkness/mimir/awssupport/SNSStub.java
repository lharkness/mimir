package com.leeharkness.mimir.awssupport;

import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirMessage;

import java.time.Instant;

public class SNSStub {
    public void sendMessage(String messageText, String destinationUser, MimirSessionContext mimirSessionContext) {
        System.out.println("*** sending message: " + messageText + " to " + destinationUser);
        MimirMessage message = MimirMessage.builder()
                .fromUserName(mimirSessionContext.getUser().getUserName())
                .sentInstant(Instant.now())
                .toUserName(destinationUser)
                .message(messageText)
                .build();
    }
}
