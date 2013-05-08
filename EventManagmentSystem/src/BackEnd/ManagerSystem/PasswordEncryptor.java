/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

/**
 *
 * @author David Tersoff
 */
public class PasswordEncryptor {
    private PublicKey pubKey;
    private PrivateKey privKey;
    private Cipher cipher;
    
    public PasswordEncryptor()
    {
        
    }
    public void setPubKey(BigInteger m, BigInteger e) throws 
            NoSuchAlgorithmException, InvalidKeySpecException
    {
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m,e);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        pubKey = fact.generatePublic(keySpec);
    }
}
