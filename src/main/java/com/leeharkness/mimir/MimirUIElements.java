package com.leeharkness.mimir;

import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import lombok.Builder;
import lombok.Data;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

@Data
@Builder
public class MimirUIElements {
    private String prompt;
    private MimirInputFacility inputFacility;
    private MimirOutputFacility outputFacility;
}
