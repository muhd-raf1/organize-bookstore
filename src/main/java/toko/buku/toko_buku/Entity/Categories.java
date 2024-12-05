package toko.buku.toko_buku.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Categories {

    @Id
    private String id;

    private String category;

    @OneToMany(mappedBy = "idCategori")
    private List<BooksEntity> books;

    public Categories() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<BooksEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BooksEntity> books) {
        this.books = books;
    }

}
