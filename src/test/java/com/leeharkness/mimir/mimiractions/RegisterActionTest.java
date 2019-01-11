package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CloudFormationStub;
import com.leeharkness.mimir.awssupport.DynamoStub;
import com.leeharkness.mimir.mimirsupport.*;
import com.leeharkness.mimir.model.MimirUser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegisterActionTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private MimirInputFacility mockInputFacility;
    @Mock
    private MimirOutputFacility mockOutputFacility;
    @Mock
    private MimirKeyGenerator mockKeyGenerator;
    @Mock
    private DynamoStub mockDynamoStub;
    @Mock
    private LoginAction mockLoginAction;
    @Mock
    private PasswordChecker mockPasswordChecker;
    @Mock
    private MimirKey mockMimirKey;
    @Mock
    private ActionResult mockActionResult;
    @Mock
    private MimirSessionContext mockMimirSessionContext;
    @Mock
    private CloudFormationStub mockCloudFormationStub;
    @Mock
    private MimirUser mockMimirUser;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private MimirUIElements mimirUIElements;

    @InjectMocks
    private RegisterAction target;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .inputFacility(mockInputFacility)
                .outputFacility(mockOutputFacility)
                .prompt("null>")
                .build();
    }

    @Test
    public void testThatRegisterUsesItsCollaboratorsCorrectly() {

        String userName = "userName";
        String password = "password";

        String input = "register " + userName;
        String loginInput = "login " + userName + " " + password;

        when(mockMimirSessionContext.getUser()).thenReturn(null);
        when(mockInputFacility.promptForPasswordUsing(anyString())).thenReturn(password);
        when(mockPasswordChecker.valid(password)).thenReturn(true);
        when(mockKeyGenerator.createKeyPairFor(userName, password)).thenReturn(mockMimirKey);
        when(mockLoginAction.handle(eq(loginInput), any(), eq(mockMimirSessionContext))).thenReturn(mockActionResult);

        ActionResult result = target.handle(input, mimirUIElements, mockMimirSessionContext);

        assertThat(result, is(mockActionResult));

        verify(mockDynamoStub).storeKey(mockMimirKey, userName);
        verify(mockCloudFormationStub).newUserSetup(mockMimirSessionContext);
    }

    @Test
    public void testThatWeDontAllowRegisterFromLoggedInUser() {

        String userName = "userName";
        String password = "password";

        String input = "register " + userName + " " + password;

        when(mockMimirSessionContext.getUser()).thenReturn(mockMimirUser);
        when(mockInputFacility.promptForPasswordUsing(anyString())).thenReturn(password);
        when(mockPasswordChecker.valid(password)).thenReturn(true);

        ActionResult result = target.handle(input, mimirUIElements, mockMimirSessionContext);

        assertThat(result, is(ActionResult.noOpResult()));

    }
}
