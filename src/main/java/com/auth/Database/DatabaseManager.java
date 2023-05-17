package com.auth.Database;

import com.auth.Entities.User;
import com.auth.Entities.UserType;
import com.auth.Security.Security;
import com.auth.View.ReturnMessagePane;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mauricio.rodrigues
 */
public class DatabaseManager {
    
    private EntityManagerFactory emf;
    private Security security;
    
    public DatabaseManager() {
        emf = Persistence.createEntityManagerFactory("myPU");
        security = new Security();
    }
    
    public void createUserType(String name, String description){
        emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Consulta para verificar se já existe um UserType com o mesmo nome
            String jpql = "SELECT COUNT(t) FROM UserType t WHERE t.nome = :name";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("name", name);

            Long count = query.getSingleResult();

            if (count > 0) {
                // Já existe um UserType com o mesmo nome, lançar exceção
                ReturnMessagePane.errorPainel("Já existe um Grupo com esse nome.");
                throw new IllegalArgumentException("Já existe um Grupo com esse nome." + name);
            }

            // Não existe UserType com o mesmo nome, criar um novo
            tx.begin();
            UserType userType = new UserType(name, description, new Date());
            em.persist(userType);
            tx.commit();
        } finally {
            em.close();
            emf.close();
        }
    }

    
    public void createUser(String nome, String email, String password, String userTypeName) {
        emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Verificar se já existe um usuário com o mesmo e-mail
            Query emailQuery = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
            emailQuery.setParameter("email", email);
            List<User> usersWithEmail = emailQuery.getResultList();
            if (!usersWithEmail.isEmpty()) {
                ReturnMessagePane.errorPainel("E-mail já está em uso.");
                throw new IllegalArgumentException("E-mail já está em uso.");
            }

            // Obter o ID do tipo de usuário pelo nome
            Query typeQuery = em.createQuery("SELECT t.id FROM UserType t WHERE t.nome = :userTypeName");
            typeQuery.setParameter("userTypeName", userTypeName);
            UUID userTypeId = (UUID) typeQuery.getSingleResult();

            // Calcular o hash da senha
            String passwordHash = security.calcularHash(password);

            // Criar o objeto User e persistir no banco de dados
            User user = new User(nome, email, passwordHash, userTypeId, new Date());
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public int login(String email, String senha) {
        //Inicializa
        emf = Persistence.createEntityManagerFactory("myPU");
        // Obter a instância do EntityManager a partir do EntityManagerFactory
        EntityManager em = emf.createEntityManager();
        try {
        // Consulta para buscar o usuário pelo email fornecido
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("email", email);

        User user = query.getSingleResult(); // tenta obter o usuário

        // Verifica se o usuário foi encontrado
        if (user != null) {
            // Se o usuário foi encontrado, verificar a senha
            if (user.getPasswordHash().equals(security.calcularHash(senha))) {
                // Se a senha estiver correta, retorna SUCCESS
                return 1;
            } else {
                // Senha incorreta
                return 2;
            }
        } else {
            // Usuário não encontrado
            return 3;
        }
        } catch (NoResultException e) {
            // Email não encontrado
            return 4;
        } finally {
            // Fechar o EntityManager e o EntityManagerFactory
            em.close();
            emf.close();
        }
    }
    
    public List<UserType> getUserType() {
        emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT ut FROM UserType ut";
            TypedQuery<UserType> query = em.createQuery(jpql, UserType.class);
            return query.getResultList();
            
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public void alterarGrupoUsuario(UserType userType) {
        emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            emf = Persistence.createEntityManagerFactory("myPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            // Atualizar o grupo de usuário na tabela
            em.merge(userType);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
    
    public void deleteGrupoUsuario(UserType userType) {
        emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            emf = Persistence.createEntityManagerFactory("myPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            // Carregar o grupo de usuário pelo ID
            UserType grupoUsuario = em.find(UserType.class, userType.getId());
            
            if (grupoUsuario != null) {
                // Excluir o grupo de usuário
                em.remove(grupoUsuario);
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

}
