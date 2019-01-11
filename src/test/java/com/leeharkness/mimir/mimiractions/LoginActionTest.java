package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;
import com.leeharkness.mimir.awssupport.DynamoStub;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginActionTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private MimirInputFacility mockInputFacility;
    @Mock
    private MimirOutputFacility mockOutputFacility;
    @Mock
    private CognitoStub mockCognitoStub;
    @Mock
    private MimirSessionContext mockMimirSessionContext;
    @Mock
    private DynamoStub mockDynamoStub;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private MimirUIElements mimirUIElements;

    @InjectMocks
    private LoginAction target;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .inputFacility(mockInputFacility)
                .outputFacility(mockOutputFacility)
                .build();
    }

    @Test
    public void testThatLoginLogsPeopleInSuccessfully() {
        String loginToken = "LOGIN_TOKEN";
        String userName = "userName";
        String password = "password";

        when(mockCognitoStub.login(userName, password, mockOutputFacility)).thenReturn(Optional.of(loginToken));

        ActionResult result = target.handle("login " + userName + " " + password, mimirUIElements,
                mockMimirSessionContext);

        assertTrue(result.isLoggedIn());
        assertFalse(result.isTerminate());

        verify(mockDynamoStub).retrieveKey(userName);

    }

    @Test
    public void testThatWePromptForUsernameAndPasswordIfNotProvided() {
        String loginToken = "LOGIN_TOKEN";
        String userName = "userName";
        String password = "password";

        when(mockCognitoStub.login(userName, password, mockOutputFacility)).thenReturn(Optional.of(loginToken));
        when(mockInputFacility.promptForInputUsing(anyString())).thenReturn(userName);
        when(mockInputFacility.promptForPasswordUsing(anyString())).thenReturn(password);

        ActionResult result = target.handle("login ", mimirUIElements,
                mockMimirSessionContext);

        assertTrue(result.isLoggedIn());
        assertFalse(result.isTerminate());

    }
}
