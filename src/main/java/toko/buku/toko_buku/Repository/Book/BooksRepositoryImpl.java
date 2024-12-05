package toko.buku.toko_buku.Repository.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import toko.buku.Utilities.UtilEntityManagerFactoy;
import toko.buku.toko_buku.Entity.BooksEntity;

public class BooksRepositoryImpl implements BooksRepository {

    private BooksEntity booksEntity;

    @Override
    public boolean insert(BooksEntity book) {

        EntityManagerFactory entityManagerFactory = UtilEntityManagerFactoy.getEntityManagerFactory();
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
            entityManager.close();
            entityManagerFactory.close();

            return true;
        } catch (Exception e) {

            System.out.println("error : " + e.getMessage());
            transaction.rollback();
            return false;
        }

    }

    @Override
    public void delete(BooksEntity book) {

        entityManagerFactory

    }

    @Override
    public void update(BooksEntity book) {

    }

    @Override
    public void findById(BooksEntity book) {

    }

    @Override
    public void findByName(BooksEntity book) {

    }

}
