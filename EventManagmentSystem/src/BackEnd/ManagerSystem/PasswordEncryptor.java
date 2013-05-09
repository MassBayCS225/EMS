/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import EMS_Database.DoesNotExistException;
import EMS_Database.impl.RootKey;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author David Tersoff
 */
public class PasswordEncryptor {

    private PublicKey pubKey;
    private PrivateKey privKey;
    private Cipher cipher;
    private RootKey root;

    public PasswordEncryptor() {
        try {
            setPubKey(root.getPubMod(), root.getPubExp());

            setPrivKey(root.getPrivMod(), root.getPrivExp());
            cipher = cipher.getInstance("RSA");
        } catch (DoesNotExistException e){
        } catch (NoSuchAlgorithmException e){
        } catch (InvalidKeySpecException e){
        } catch (NoSuchPaddingException e){
        }
    }

    public void setPubKey(BigInteger m, BigInteger e) {
        try {
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            pubKey = fact.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException x) {
        } catch (InvalidKeySpecException x) {
        }
    }

    public void setPrivKey(BigInteger m, BigInteger e) throws
            NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        privKey = fact.generatePrivate(keySpec);
    }

    public String encrypt(String message) throws InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] stringBytes = message.getBytes("UTF8");

        byte[] raw = cipher.doFinal(stringBytes);
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(raw);
        return base64;
    }

    public String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, privKey);
        } catch (InvalidKeyException e) {
        }

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] raw;
        try {
            raw = decoder.decodeBuffer(encrypted);
        } catch (IOException e) {
            raw = new byte[0];
        }
        byte[] stringBytes;
        try {
            stringBytes = cipher.doFinal(raw);
        } catch (IllegalBlockSizeException e) {
            stringBytes = new byte[0];
        } catch (BadPaddingException e) {
            stringBytes = new byte[0];
        }
        String clear = "";
        try {
            clear = new String(stringBytes, "UTF8");
        } catch (UnsupportedEncodingException e) {
        }

        return clear;
    }
}
