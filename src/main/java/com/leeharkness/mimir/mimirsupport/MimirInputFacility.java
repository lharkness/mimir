package com.leeharkness.mimir.mimirsupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.beryx.textio.TextIO;

@AllArgsConstructor
@Builder
public class MimirInputFacility {
    private TextIO textIO;

    public String promptForInputUsing(String prompt) {
        return textIO.newStringInputReader().read(prompt);
    }

    public String promptForPasswordUsing(String prompt) {
        return textIO.newStringInputReader().withInputMasking(true).read(prompt);
    }
}
