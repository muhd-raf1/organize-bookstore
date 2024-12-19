package toko.buku.toko_buku.Repository.Category;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
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
    public List<CategoriesEntity> findBooksSameGenre(CategoriesEntity categoriesEntity) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<CategoriesEntity> query = criteriaBuilder.createQuery(CategoriesEntity.class);
            Root<CategoriesEntity> c = query.from(CategoriesEntity.class);
            c.fetch("books", JoinType.LEFT);

            query.select(c);

            query.where(
                    criteriaBuilder.equal(c.get("id"), categoriesEntity.getId()));

            TypedQuery<CategoriesEntity> typedQuery = entityManager.createQuery(query);
            List<CategoriesEntity> resultList = typedQuery.getResultList();

            transaction.commit();

            if (resultList.isEmpty()) {
                return null;
            } else {
                return resultList;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();

            return null;
        } finally {
            entityManager.close();
            System.out.println("entityManager closed!");
            entityManagerFactory.close();
            System.out.println("entityManagerFactory closed !");
        }
    }

}
