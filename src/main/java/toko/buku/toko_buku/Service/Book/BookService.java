package toko.buku.toko_buku.Service.Book;

import toko.buku.toko_buku.Entity.BooksEntity;

public interface BookService {

    public boolean add(BooksEntity book);

    public void delete(String id);

    public void update(BooksEntity book);

    public void findByIdAndName(String id, String name);
}
