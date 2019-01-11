package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.datalayer.MessageDao;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirMessage;
import com.leeharkness.mimir.model.MimirUser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SendMessageActionTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private MimirSessionContext mockMimirSessionContext;
    @Mock
    private MessageDao mockMessageDao;
    @Mock
    private MimirInputFacility mockInputFacility;
    @Mock
    private MimirOutputFacility mockOutputFacility;
    @Mock
    private MimirMessage mockMimirMessage;
    @Mock
    private MimirUser mockMimirUser;

    private MimirUIElements mimirUIElements;
    private List<MimirMessage> messages;

    @InjectMocks
    private SendMessageAction target;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .outputFacility(mockOutputFacility)
                .inputFacility(mockInputFacility)
                .build();
        messages = Collections.singletonList(mockMimirMessage);
    }

    @Test
    public void testThatWeCanSendAMessage() {

        String input = "s \"this is a test message\" lee2";

        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);

        ActionResult result = target.handle(input, mimirUIElements, mockMimirSessionContext);

        verify(mockMessageDao).sendMessage(any(), any());
    }
}
