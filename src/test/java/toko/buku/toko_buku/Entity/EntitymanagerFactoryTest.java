package toko.buku.toko_buku.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManagerFactory;
import toko.buku.Utilities.UtilEntityManagerFactoy;

public class EntitymanagerFactoryTest {

    @Test
    void testEntitymanagerFactoryNotNull() {
        EntityManagerFactory entityManagerFactory = UtilEntityManagerFactoy.getEntityManagerFactory();

        Assertions.assertNotNull(entityManagerFactory);

        entityManagerFactory.close();
    }
}
