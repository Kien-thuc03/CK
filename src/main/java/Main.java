import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @description
 * @author: nktng,
 * @date:23/05/2024,
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQLBD");
        EntityManager em = emf.createEntityManager();
    }
}
