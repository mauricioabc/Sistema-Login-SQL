package com.auth.Security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author mauricio.rodrigues
 */
public class Security {

    public String calcularHash(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512"); // escolhe o algoritmo de hash
            byte[] hashBytes = md.digest(senha.getBytes()); // gera o hash em bytes
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b)); // converte os bytes para hexadecimal
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao calcular o hash da senha", e);
        }
    }   
}