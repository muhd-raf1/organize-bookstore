package toko.buku.toko_buku.Service.Book;

import toko.buku.toko_buku.Entity.BooksEntity;

public interface BookService {

    public boolean add(BooksEntity book);

    public boolean delete(String id);

    public boolean update(String id, BooksEntity book);

    public void findByIdAndName(String id, String name);
}
