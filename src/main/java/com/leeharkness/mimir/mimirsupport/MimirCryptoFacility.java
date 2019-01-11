package com.leeharkness.mimir.mimirsupport;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MimirCryptoFacility {

    private Cipher cipher;
    Base64.Encoder encoder;
    Base64.Decoder decoder;

    public MimirCryptoFacility() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance("RSA");
        encoder = Base64.getEncoder();
        decoder = Base64.getDecoder();
    }

    public String encryptMessage(String message, String  publicKeyString) throws InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException {
        PublicKey key = makePublicKeyFrom(publicKeyString);
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return encoder.encodeToString(this.cipher.doFinal(message.getBytes()));
    }

    public String decryptMessage(String cipherText, String privateKeyString) throws InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException {
        PrivateKey key = makePrivateKeyFrom(privateKeyString);
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String((this.cipher.doFinal(decoder.decode(cipherText))));
    }

    private PublicKey makePublicKeyFrom(String publicKeyString) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        byte[] keyBytes = decoder.decode(publicKeyString);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        return kf.generatePublic(spec);
    }

    private PrivateKey makePrivateKeyFrom(String privateKeyString) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        byte[] keyBytes = decoder.decode(privateKeyString);
        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        return kf.generatePrivate(spec);
    }

}
