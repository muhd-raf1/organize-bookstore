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
            boolean bookRepo = booksRepository.insert(book);
            return bookRepo;

        } else {
            return false;
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(BooksEntity book) {

    }

    @Override
    public void findByIdAndName(String id, String name) {

    }

}
