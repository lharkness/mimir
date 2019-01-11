package com.leeharkness.mimir.awssupport;

import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirMessage;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class SQSStub {
    public List<MimirMessage> getMessagesFor(MimirSessionContext mimirSessionContext) {
        System.out.println("*** retrieving messages");
        MimirMessage message = MimirMessage.builder().fromUserName("Lee").sentInstant(Instant.now())
                .toUserName("Lee2").build();
        return Collections.singletonList(message);
    }
}
