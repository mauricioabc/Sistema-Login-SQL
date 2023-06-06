package com.auth.Security;

import com.auth.Database.DatabaseManager;
import com.auth.Entities.Config;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Security {

    public boolean generateKeyPair(){
        GenerateKeys geraChaves = new GenerateKeys();
        KeyPair keyPair = geraChaves.generateKeys();
        
        String publicKey, privateKey;
        publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        
        Config config = new Config(publicKey, privateKey);
        
        DatabaseManager banco = new DatabaseManager();
        banco.createConfig(config);
        return true;
    }
    
    public PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException{
        DatabaseManager banco = new DatabaseManager();
        byte[] publicKeyBytes = Base64.getDecoder().decode(banco.getPublicKey());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
    
    public String encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException, Exception{
        CryptographyManager cripto = new CryptographyManager();
        String textoCriptografadoComChavePublica = cripto.encrypt(password, getPublicKey());
        return textoCriptografadoComChavePublica;
    }
    
    public PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        DatabaseManager banco = new DatabaseManager();
        byte[] privateKeyBytes = Base64.getDecoder().decode(banco.getPrivateKey());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
    
    public String decryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException, Exception{
        CryptographyManager cripto = new CryptographyManager();
        String textoDecriptografadoComChavePrivada = cripto.decrypt(password, getPrivateKey());
        return textoDecriptografadoComChavePrivada;
    }
    
}
