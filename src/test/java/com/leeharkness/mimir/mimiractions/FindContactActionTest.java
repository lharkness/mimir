package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.datalayer.ContactDao;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirContact;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class FindContactActionTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private ContactDao mockContactDao;
    @Mock
    private MimirInputFacility mockInputFacility;
    @Mock
    private MimirOutputFacility mockOutputFacility;
    @Mock
    private MimirSessionContext mockMimirSessionContext;
    @Mock
    private MimirUser mockMimirUser;

    @InjectMocks
    private FindContactAction target;

    private MimirUIElements mimirUIElements;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .outputFacility(mockOutputFacility)
                .inputFacility(mockInputFacility)
                .build();
    }

    @Test
    public void testThatFindContactsUsesItsCollaboratorsAsExpected() {
        MimirContact mimirContact = MimirContact.builder()
                .userName("lee")
                .build();
        List<MimirContact> expectedContactList = Collections.singletonList(mimirContact);

        when(mockMimirUser.getUserName()).thenReturn("lee");
        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);
        when(mockContactDao.searchContacts("lee")).thenReturn(expectedContactList);

        String input = "f lee";

        ActionResult result = target.handle(input, mimirUIElements, mockMimirSessionContext);

        assertThat(result.getResultData("contacts"), is(expectedContactList));
    }


}
