package com.leeharkness.mimir.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MimirContact {
    private String userName;
    private String publicKey;
}
