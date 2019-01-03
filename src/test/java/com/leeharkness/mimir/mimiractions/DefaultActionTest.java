package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class DefaultActionTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private TextIO mockTextIO;
    @Mock
    private TextTerminal<?> mockTextTerminal;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private MimirUIElements mimirUIElements;

    private DefaultAction target;

    @Before
    public void setup() {
        this.target = new DefaultAction();
        mimirUIElements = MimirUIElements.builder()
                .textTerminal(mockTextTerminal)
                .textIO(mockTextIO)
                .build();
    }

    @Test
    public void testThatDefaultActionPrintsAMessage() {
        ActionResult result = target.handle("input", mimirUIElements);

        assertThat(result.isTerminate(), is(false));
        verify(mockTextTerminal).println(stringCaptor.capture());
        assertTrue(stringCaptor.getValue().contains("Unknown"));
    }

}
