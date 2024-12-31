package toko.buku.Utilities;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UtilEntityManagerFactoy {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence
                    .createEntityManagerFactory("Project_Bookstore");
            return entityManagerFactory;
        }

        return entityManagerFactory;
    }

    public static void closeEntityManagerfactory() {

        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}
