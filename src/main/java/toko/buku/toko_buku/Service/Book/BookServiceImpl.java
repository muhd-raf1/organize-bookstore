package toko.buku.toko_buku.Service.Book;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;

public class BookServiceImpl implements BookService {

    private BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    private boolean isNull(BooksEntity book) {
        return book.getAuthor() != null && book.getCreatedAt() != null && book.getDescription() != null &&
                book.getId() != null && book.getIdCategori() != null && book.getPages() != null
                && book.getPublisher() != null && book.getTitle() != null && book.getUpdatedAt() != null
                && book.getYearOfPublish() != null;
    }

    private boolean isEmptyString(BooksEntity book) {
        return book.getAuthor().trim() != "" && book.getDescription().trim() != "" &&
                book.getId().trim() != "" && book.getPages() > 0 && book.getPublisher().trim() != ""
                && book.getTitle().trim() != "";
    }

    @Override
    public boolean add(BooksEntity book) {

        if (isNull(book) && isEmptyString(book)) {
            boolean bookRepoInsert = booksRepository.insert(book);
            return bookRepoInsert;

        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {

        if (id.trim() != "") {
            boolean bookRepoDelete = booksRepository.delete(new BooksEntity(id));
            return bookRepoDelete;
        } else {
            return false;
        }

    }

    @Override
    public boolean update(String id, BooksEntity book) {

        BooksEntity booksEntity = new BooksEntity();

        if (book != null && (id != null && id.trim() != "")) {

            if (book.getAuthor() != null && book.getAuthor().trim() != "")
                booksEntity.setAuthor(book.getAuthor());

            if (book.getDescription() != null && book.getDescription().trim() != "")
                booksEntity.setDescription(book.getDescription());

            if (book.getId() != null && book.getId().trim() != "")
                booksEntity.setId(book.getId());

            if (book.getPages() != null && book.getPages() >= 0)
                booksEntity.setPages(book.getPages());

            if (book.getPublisher() != null && book.getPublisher().trim() != "")
                booksEntity.setPublisher(book.getPublisher());

            if (book.getTitle() != null && book.getTitle().trim() != "")
                booksEntity.setTitle(book.getTitle());

            if (book.getYearOfPublish() != null)
                booksEntity.setYearOfPublish(book.getYearOfPublish());

            boolean update = booksRepository.update(id, booksEntity);

            return update;
        } else {
            return false;
        }

    }

    @Override
    public BooksEntity findByIdAndName(String id, String name) {

        if (id != null || name != null) {

            BooksEntity resultBook = booksRepository.findById(new BooksEntity(id, name));
            return resultBook;
        } else {
            return null;
        }
    }

}
