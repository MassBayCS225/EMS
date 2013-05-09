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
    
    public PasswordEncryptor() throws
            NoSuchAlgorithmException, InvalidKeySpecException, 
            DoesNotExistException, NoSuchPaddingException
    {
        setPubKey(root.getPubMod(), root.getPubExp());
        setPrivKey(root.getPrivMod(), root.getPrivExp());
        cipher = cipher.getInstance("RSA");
    }
    public void setPubKey(BigInteger m, BigInteger e) throws 
            NoSuchAlgorithmException, InvalidKeySpecException
    {
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m,e);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        pubKey = fact.generatePublic(keySpec);
    }
    public void setPrivKey(BigInteger m, BigInteger e) throws
            NoSuchAlgorithmException, InvalidKeySpecException
    {
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        privKey = fact.generatePrivate(keySpec);
    }
    public String encrypt(String message) throws InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException
    {
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        
        byte[] stringBytes = message.getBytes("UTF8");
        
        byte[] raw = cipher.doFinal(stringBytes);
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(raw);
        return base64;
    }
    public String decrypt(String encrypted) throws InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, IOException
    {
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] raw = decoder.decodeBuffer(encrypted);
        byte[] stringBytes = cipher.doFinal(raw);
        
        String clear = new String(stringBytes, "UTF8");
        
        return clear;
    }
}
