package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.SQSStub;
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RetrieveMessagesActionTest {

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
    private RetrieveMessagesAction target;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .outputFacility(mockOutputFacility)
                .inputFacility(mockInputFacility)
                .build();
        messages = Collections.singletonList(mockMimirMessage);
    }

    @Test
    public void testThatRetrieveMessagesUsesItsCollaboratorsCorrectly() {

        String input = "r";

        when(mockMessageDao.retrieveMessages(mockMimirSessionContext)).thenReturn(messages);

        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);

        ActionResult result = target.handle(input, mimirUIElements, mockMimirSessionContext);

        assertThat(result.getResultData("messages"), is(messages));
    }
}
