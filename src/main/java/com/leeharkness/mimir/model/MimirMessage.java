package com.leeharkness.mimir.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class MimirMessage {
    private MimirContact mimirContact;
    private String fromUserName;
    private Instant sentInstant;
    private String message;
}
