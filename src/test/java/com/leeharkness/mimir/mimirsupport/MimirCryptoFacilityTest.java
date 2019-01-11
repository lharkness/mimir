package com.leeharkness.mimir.mimirsupport;

import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class MimirCryptoFacilityTest {

    public static final String TEST_MESSAGE = "This is a test message";

    MimirCryptoFacility target;

    @Before
    public void setup() throws NoSuchAlgorithmException, NoSuchPaddingException {
        target = new MimirCryptoFacility();
    }

    @Test
    public void testThatWeCanGetThisFar() {
        assertNotNull(target);
    }

    @Test
    public void testThatWeCanEncryptAndDecrypt() throws IllegalBlockSizeException, BadPaddingException,
            InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException {
        MimirKeyGenerator keyGenerator = new MimirKeyGenerator();
        MimirKey key = keyGenerator.createKeyPairFor("Lee", "Lee");
        String publicKeyString = key.getPublicKey();
        String encryptedMessage = target.encryptMessage(TEST_MESSAGE, publicKeyString);
        String decryptedMessage = target.decryptMessage(encryptedMessage, key.getPrivateKey());

        assertThat(decryptedMessage, is(TEST_MESSAGE));
    }

}
