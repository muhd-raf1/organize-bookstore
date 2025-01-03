package toko.buku.toko_buku.Service.Category;

import java.util.List;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;

public class CategoryServiceImpl implements CategoryService {

    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();

    public CategoryServiceImpl(CategoriRepository categoriRepository) {
        this.categoriRepository = categoriRepository;
    }

    private boolean isNull(CategoriesEntity category) {
        return category.getId() != null && category.getCategory() != null;
    }

    private boolean isEmptyString(CategoriesEntity category) {
        return category.getId().trim() != "" && category.getCategory().trim() != "";
    }

    @Override
    public boolean add(CategoriesEntity category) {

        if (isNull(category) && isEmptyString(category)) {
            categoriRepository.insert(category);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public List<BooksEntity> findBooksSameCategory(String categoryName) {

        if (categoryName != null && categoryName.trim() != "") {
            BooksEntity book = new BooksEntity();
            book.setIdCategori(new CategoriesEntity(categoryName));

            List<BooksEntity> booksSameGenre = categoriRepository.findBooksSameGenre(book);
            return booksSameGenre;
        } else {
            return null;
        }
    }

    @Override
    public List<CategoriesEntity> findAllCategories() {
        List<CategoriesEntity> all = categoriRepository.findAll();

        return all;
    }

}
