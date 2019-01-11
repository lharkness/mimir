package com.leeharkness.mimir.mimiractions;

import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.awssupport.CognitoStub;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

public class LogoutActionTest {
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

    private MimirUIElements mimirUIElements;

    @InjectMocks
    private LogoutAction target;

    @Before
    public void setup() {
        mimirUIElements = MimirUIElements.builder()
                .inputFacility(mockInputFacility)
                .outputFacility(mockOutputFacility)
                .build();
    }

    @Test
    public void testThatWeLogoutUsersCorrectly() {

        String userName = "userName";
        String[] input = new String[] {"logout", userName};
        ActionResult actionResult = target.actionSpecificHandle(input, mimirUIElements, mockMimirSessionContext);

        assertFalse(actionResult.isTerminate());
        assertFalse(actionResult.isLoggedIn());

        verify(mockMimirSessionContext).clear();

    }
}
