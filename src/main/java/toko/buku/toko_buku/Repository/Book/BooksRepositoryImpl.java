package toko.buku.toko_buku.Repository.Book;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import toko.buku.Utilities.UtilEntityManagerFactoy;
import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;

public class BooksRepositoryImpl implements BooksRepository {

    private EntityManagerFactory entityManagerFactory = UtilEntityManagerFactoy.getEntityManagerFactory();
    private BooksEntity booksEntity;

    @Override
    public boolean insert(BooksEntity book) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            booksEntity = new BooksEntity();
            booksEntity.setAuthor(book.getAuthor());
            booksEntity.setCreatedAt(book.getCreatedAt());
            booksEntity.setDescription(book.getDescription());
            booksEntity.setId(book.getId());
            booksEntity.setPages(book.getPages());
            booksEntity.setPublisher(book.getPublisher());
            booksEntity.setTitle(book.getTitle());
            booksEntity.setYearOfPublish(book.getYearOfPublish());
            booksEntity.setIdCategori(book.getIdCategori());
            booksEntity.setUpdatedAt(book.getUpdatedAt());

            entityManager.persist(booksEntity);

            transaction.commit();

            return true;
        } catch (Exception e) {

            System.out.println("error : " + e.getMessage());
            transaction.rollback();
            return false;

        } finally {
            entityManager.close();
            System.out.println("entityManager closed !");
            entityManagerFactory.close();
            System.out.println("entitymanagerFactory closed !");
        }

    }

    @Override
    public boolean delete(BooksEntity book) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            BooksEntity booksEntity = entityManager.find(BooksEntity.class, book.getId());

            entityManager.remove(booksEntity);

            transaction.commit();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        } finally {
            entityManager.close();
            System.out.println("entityManager closed !");
            entityManagerFactory.close();
            System.out.println("entitymanagerFactory closed !");
        }

    }

    @Override
    public boolean update(String id, BooksEntity book) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            BooksEntity booksEntity = entityManager.find(BooksEntity.class, id);

            if (booksEntity == null) {

                transaction.rollback();
                return false;

            } else {
                if (book.getAuthor() != null)
                    booksEntity.setAuthor(book.getAuthor());
                if (book.getDescription() != null)
                    booksEntity.setDescription(book.getDescription());
                if (book.getId() != null)
                    booksEntity.setId(book.getId());
                if (book.getPages() != null)
                    booksEntity.setPages(book.getPages());
                if (book.getPublisher() != null)
                    booksEntity.setPublisher(book.getPublisher());
                if (book.getTitle() != null)
                    booksEntity.setTitle(book.getTitle());
                if (book.getYearOfPublish() != null)
                    booksEntity.setYearOfPublish(book.getYearOfPublish());

                entityManager.merge(booksEntity);
            }

            transaction.commit();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;

        } finally {
            entityManager.close();
            System.out.println("entityManager closed !");
            entityManagerFactory.close();
            System.out.println("entityManagerFactory closed !");
        }
    }

    @Override
    public BooksEntity findByIdOrTitle(BooksEntity book) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<BooksEntity> query = criteriaBuilder
                    .createQuery(BooksEntity.class);
            Root<BooksEntity> b = query.from(BooksEntity.class);
            b.fetch("idCategori", JoinType.LEFT);

            query.select(b);

            Predicate condition1 = criteriaBuilder.equal(b.get("id"), book.getId());
            Predicate condition2 = criteriaBuilder.equal(b.get("title"), book.getTitle());

            Predicate or = criteriaBuilder.or(condition1, condition2);

            query.where(or);

            TypedQuery<BooksEntity> typedQuery = entityManager.createQuery(query);
            BooksEntity singleResult = typedQuery.getSingleResult();

            transaction.commit();

            return singleResult;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();

            return null;
        } finally {
            entityManager.close();
            System.out.println("entityManager clossed !");
            entityManagerFactory.close();
            System.out.println("entityMangerFactory closed !");
        }

    }
}
