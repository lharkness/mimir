package com.leeharkness.mimir.model;

import com.leeharkness.mimir.mimirsupport.MimirKey;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MimirUser {
    private String userName;
    private String email;
    private MimirKey mimirKey;
    private String backendToken;
}
