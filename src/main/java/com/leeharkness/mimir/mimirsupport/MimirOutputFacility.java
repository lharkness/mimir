package com.leeharkness.mimir.mimirsupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.beryx.textio.TextTerminal;

@AllArgsConstructor
@Builder
public class MimirOutputFacility {
    private TextTerminal<?> textTerminal;

    public void output(String string) {
        textTerminal.println(string);
    }
}
