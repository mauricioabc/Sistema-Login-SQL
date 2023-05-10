import com.auth.Database.DatabaseManager;
import com.auth.Entities.User;
import com.auth.Entities.UserType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mauricio.rodrigues
 */
public class testeBanco {
    
    public testeBanco() {
    }

    @Test
    public void testeCriaUserAndUserType() {
        // Cria uma instância do EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

        // Cria uma instância do EntityManager
        EntityManager em = emf.createEntityManager();

        // Inicia uma transação
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Cria um objeto UserType e define seus atributos
        UserType userType = new UserType("Administrador", "Usuário com acesso total ao sistema", new Date());

        // Salva o objeto UserType no banco de dados
        em.persist(userType);

        // Cria um objeto User e define seus atributos, incluindo o userTypeId como o id do objeto UserType criado anteriormente
        User user = new User("João da Silva", "joao.silva@example.com", "123456", userType.getId(), new Date());

        // Salva o objeto User no banco de dados
        em.persist(user);

        // Finaliza a transação
        tx.commit();

        // Fecha o EntityManager
        em.close();

        // Fecha o EntityManagerFactory
        emf.close();
    }
    
    @Test
    public void criaUserType(){
        DatabaseManager banco = new DatabaseManager();
        banco.createUserType("Aluno", "Usuário com acesso de aluno");
    }
    
    @Test
    public void criaUser(){
        DatabaseManager banco = new DatabaseManager();
        banco.createUser("João", "pedro@gmail.com", "teste123", "Aluno");
    }
    
    @Test
    public void login(){
        DatabaseManager banco = new DatabaseManager();
        int st = banco.login("joao@gmail.com", "teste123");

        switch (st) {
            case 1:
                System.out.println("Login bem sucedido");
                break;
            case 4:
                System.out.println("Email nao encontrado");
                break;
            case 2:
                System.out.println("Senha incorreta");
                break;
            case 3:
                System.out.println("Usuário nao encontrado");
                break;
            default:
                System.out.println("Erro desconhecido");
        }
    }
    
}
