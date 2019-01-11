package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExitActionTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private MimirInputFacility mockInputFacility;
    @Mock
    private MimirOutputFacility mockOutputFacility;
    @Mock
    private MimirSessionContext mockMimirSessionContext;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private MimirUIElements mimirUIElements;

    private ExitAction target;

    @Before
    public void setup() {
        this.target = new ExitAction();
        mimirUIElements = MimirUIElements.builder()
                .outputFacility(mockOutputFacility)
                .inputFacility(mockInputFacility)
                .build();
    }

    @Test
    public void testThatExitActionExits() {
        ActionResult result = target.handle("", mimirUIElements, mockMimirSessionContext);

        assertTrue(result.isTerminate());
        assertThat(result.getExitCode(), is(0));
    }

}
