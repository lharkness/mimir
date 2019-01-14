package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirUser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindActionTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private FindContactAction mockFindContactAction;
    @Mock
    private FindLocalMessageAction mockFindLocalMessageAction;
    @Mock
    private MimirInputFacility mockInputFacility;
    @Mock
    private MimirOutputFacility mockOutputFacility;
    @Mock
    private MimirSessionContext mockMimirSessionContext;
    @Mock
    private MimirUser mockMimirUser;

    @InjectMocks
    private FindAction target;

    private MimirUIElements mimirUIElements;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .outputFacility(mockOutputFacility)
                .inputFacility(mockInputFacility)
                .build();
    }

    @Test
    public void testThatWeDelegateContactSearchesToFindContact() {
        String inputString = "find contact lee";
        String inputStringForFindContact = "fc lee";

        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);

        target.handle(inputString, mimirUIElements, mockMimirSessionContext);

        verify(mockFindContactAction).handle(inputStringForFindContact, mimirUIElements,
                mockMimirSessionContext);

    }

    @Test
    public void testThatWeDelegateMessageSearchesToFindMessage() {
        String inputString = "find message lee";
        String inputStringForFindMessage = "fm lee";

        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);

        target.handle(inputString, mimirUIElements, mockMimirSessionContext);

        verify(mockFindLocalMessageAction).handle(inputStringForFindMessage, mimirUIElements,
                mockMimirSessionContext);

    }

    @Test
    public void testThatUnknownSearchTypeGivesUsNoOpResult() {
        String inputString = "find bogus lee";

        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);

        ActionResult result = target.handle(inputString, mimirUIElements, mockMimirSessionContext);

        assertThat(result, is(ActionResult.noOpResult()));
    }


}
