package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.datalayer.ContactDao;
import com.leeharkness.mimir.datalayer.LocalMessageDb;
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
import static org.mockito.Mockito.*;

public class FindLocalMessageActionTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private LocalMessageDb mockLocalMessageDb;
    @Mock
    private MimirInputFacility mockInputFacility;
    @Mock
    private MimirOutputFacility mockOutputFacility;
    @Mock
    private MimirSessionContext mockMimirSessionContext;
    @Mock
    private MimirUser mockMimirUser;
    @Mock
    private MimirMessage mockMimirMessage;

    @InjectMocks
    private FindLocalMessageAction target;

    private MimirUIElements mimirUIElements;
    private List<MimirMessage> localMessages;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .outputFacility(mockOutputFacility)
                .inputFacility(mockInputFacility)
                .build();

        localMessages = Collections.singletonList(mockMimirMessage);
    }

    @Test
    public void testThatFindLocalMessageUsesItsCollaboratorsAsExpected() {

        String input = "fm lee";

        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);
        when(mockLocalMessageDb.getLocalMessages(mockMimirSessionContext)).thenReturn(localMessages);

        ActionResult result = target.handle(input, mimirUIElements, mockMimirSessionContext);

        //noinspection unchecked
        List<MimirMessage> returnedMesages = (List<MimirMessage>)result.getResultData("messages");

        assertThat(returnedMesages, is(localMessages));

        verify(mockLocalMessageDb).getLocalMessages(mockMimirSessionContext);
    }

}
