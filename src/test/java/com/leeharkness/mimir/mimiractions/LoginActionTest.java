package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
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

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private MimirUIElements mimirUIElements;

    private LoginAction target;

    @Before
    public void setup() {
        this.target = new LoginAction(mockCognitoStub);
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

        ActionResult result = target.handle("login " + userName + " " + password, mimirUIElements);

        assertTrue(result.isLoggedIn());
        assertFalse(result.isTerminate());
        assertThat(result.getPrompt(), is(userName + ">"));
        assertThat(result.getBackEndToken(), is(loginToken));
    }

}
