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
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;

        } finally {
            entityManager.close();
            System.out.println("entityManager closed");
            entityManagerFactory.close();
            System.out.println("entityManagerfactory closed");
        }

    }

    @Override
    public boolean delete(CategoriesEntity category) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CategoriesEntity categoriesEntity = entityManager
                    .find(CategoriesEntity.class, category.getId());

            entityManager.remove(categoriesEntity);

            transaction.commit();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;

        } finally {
            entityManager.close();
            System.out.println("entityManager closed");
            entityManagerFactory.close();
            System.out.println("entityManagerfactory closed");
        }

    }

    @Override
    public void update(CategoriesEntity category) {

    }

    @Override
    public void findBooksSameGenre() {

    }

}
