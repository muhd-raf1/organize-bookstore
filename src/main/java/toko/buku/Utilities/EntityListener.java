package toko.buku.Utilities;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import toko.buku.toko_buku.Entity.BooksEntity;

public class EntityListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdated(Object object) {

        BooksEntity books = (BooksEntity) object;
        books.setUpdatedAt(LocalDateTime.now());
    }

}
