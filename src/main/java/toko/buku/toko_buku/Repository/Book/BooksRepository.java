package toko.buku.toko_buku.Repository.Book;

import toko.buku.toko_buku.Entity.BooksEntity;

public interface BooksRepository {

    public boolean insert(BooksEntity book);

    public boolean delete(BooksEntity book);

    public void update(String id, BooksEntity book);

    public BooksEntity findById(BooksEntity book);
}
