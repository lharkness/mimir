package com.leeharkness.mimir.mimirsupport;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MimirKeyGenerator {

    private KeyPairGenerator keyPairGenerator;

    public MimirKeyGenerator() throws NoSuchAlgorithmException {
        keyPairGenerator= KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
    }

    public MimirKey createKeyPairFor(String userName, String password) {
        System.out.println("*** Generating Public/Private Key Pair");
        KeyPair keyPair = this.keyPairGenerator.generateKeyPair();

        Base64.Encoder base64encoder = Base64.getEncoder();

        MimirKey mimirKey = MimirKey.builder()
                .privateKey(base64encoder.encodeToString(keyPair.getPrivate().getEncoded()))
                .publicKey(base64encoder.encodeToString(keyPair.getPublic().getEncoded()))
                .build();

        return mimirKey;
    }
}
