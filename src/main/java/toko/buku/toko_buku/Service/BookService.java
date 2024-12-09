package toko.buku.toko_buku.Service;

import java.util.List;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;

public interface BookService {

    public void add(BooksEntity book);

    public void delete(String bookDelete);

    public void update(String idBook, BooksEntity book);

    public void findByBook(String idBook);

    public List<BooksEntity> findByCategory(CategoriesEntity category);

}
