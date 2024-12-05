package toko.buku.toko_buku.Repository.Book;

public interface BooksRepository {

    public void insert(BooksRepository book);

    public void delete(BooksRepository book);

    public void update(BooksRepository book);

    public void findById(BooksRepository book);

    public void findByName(BooksRepository book);
}
