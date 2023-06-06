package com.auth.Security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 *
 * @author mauricio.rodrigues
 */
public class GenerateKeys {
    
    public KeyPair generateKeys(){
        try {
            // Adiciona o provedor Bouncy Castle
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            // Gera um par de chaves pública e privada
            KeyPair keyPair = generateKeyPair();

            return keyPair;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Gera um par de chaves pública e privada usando RSA
    private KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(2048); // Tamanho da chave (em bits)
        return keyPairGenerator.generateKeyPair();
    }
    
}

