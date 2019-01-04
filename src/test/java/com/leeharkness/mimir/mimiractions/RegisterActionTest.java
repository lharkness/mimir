package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;
import com.leeharkness.mimir.awssupport.DynamoStub;
import com.leeharkness.mimir.mimirsupport.*;
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
import static org.mockito.Matchers.anyString;
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
                .build();
    }

    @Test
    public void testThatRegisterUsesItsCollaboratorsCorrectly() {

        String userName = "userName";
        String password = "password";

        String input = "register " + userName;
        String loginInput = "login " + userName + " " + password;

        when(mockInputFacility.promptForPasswordUsing(anyString())).thenReturn(password);
        when(mockPasswordChecker.valid(password)).thenReturn(true);
        when(mockKeyGenerator.createKeyPairFor(userName, password)).thenReturn(mockMimirKey);
        when(mockLoginAction.handle(loginInput, mimirUIElements)).thenReturn(mockActionResult);

        ActionResult result = target.handle(input, mimirUIElements);

        assertThat(result, is(mockActionResult));

        verify(mockDynamoStub).storeKey(mockMimirKey, userName);
    }
}
