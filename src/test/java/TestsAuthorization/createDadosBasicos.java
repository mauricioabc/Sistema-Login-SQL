package TestsAuthorization;

import com.auth.Database.DatabaseManager;
import com.auth.Security.Security;
import org.junit.Test;

/**
 *
 * @author mauricio.rodrigues
 */
public class createDadosBasicos {
    
    public createDadosBasicos() {
    }
    
    @Test
    public void createDadosBasicos() throws Exception{
        DatabaseManager banco = DatabaseManager.getInstance();
        Security sec = Security.getInstance();
        sec.generateKeyPair();
        banco.createUserType("Administrador", "Usuário com acesso de administrador");
        banco.createUser("Maurício", "mauricio@gmail.com", "teste123", "Administrador");
    }
}
