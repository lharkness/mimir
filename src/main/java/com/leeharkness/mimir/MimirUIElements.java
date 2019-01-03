package com.leeharkness.mimir;

import lombok.Builder;
import lombok.Data;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

@Data
@Builder
public class MimirUIElements {
    private String prompt;
    private TextIO textIO;
    private TextTerminal<?> textTerminal;
}
