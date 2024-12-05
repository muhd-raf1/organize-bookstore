package toko.buku.toko_buku.Repository.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import toko.buku.Utilities.UtilEntityManagerFactoy;
import toko.buku.toko_buku.Entity.CategoriesEntity;

public class CategoryRepositoryImpl implements CategoriRepository {

    private EntityManagerFactory entityManagerFactory = UtilEntityManagerFactoy.getEntityManagerFactory();

    @Override
    public boolean insert(CategoriesEntity category) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            entityManager.persist(category);

            transaction.commit();
            entityManager.close();
            entityManagerFactory.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }

    }

    @Override
    public void delete(CategoriesEntity category) {

    }

    @Override
    public void update(CategoriesEntity category) {

    }

    @Override
    public void findBooksSameGenre() {

    }

}
