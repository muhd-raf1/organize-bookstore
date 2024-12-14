package toko.buku.toko_buku.Service.Book;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;

public class BookServiceImpl implements BookService {

    private BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public boolean add(BooksEntity book) {

        if (book != null) {
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
    public void update(BooksEntity book) {

    }

    @Override
    public void findByIdAndName(String id, String name) {

    }

}
