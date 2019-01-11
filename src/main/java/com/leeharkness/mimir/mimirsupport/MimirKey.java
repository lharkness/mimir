package com.leeharkness.mimir.mimirsupport;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MimirKey {
    private String publicKey;
    private String privateKey;
}
