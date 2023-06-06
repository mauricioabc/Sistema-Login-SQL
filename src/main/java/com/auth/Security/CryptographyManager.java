package com.auth.Security;

import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 *
 * @author mauricio.rodrigues
 */
public class CryptographyManager {
    
    // Criptografar o texto usando a chave pública
    public String encrypt(String texto, PublicKey chavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
        byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

    // Descriptografar o texto usando a chave privada
    public String decrypt(String textoCriptografado, PrivateKey chavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
        byte[] textoDescriptografado = cipher.doFinal(Base64.getDecoder().decode(textoCriptografado));
        return new String(textoDescriptografado);
    }
    
}
