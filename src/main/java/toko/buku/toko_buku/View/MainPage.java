package toko.buku.toko_buku.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;
import toko.buku.toko_buku.Service.Category.CategoryService;
import toko.buku.toko_buku.Service.Category.CategoryServiceImpl;

@WebServlet(urlPatterns = "/mainPage")
public class MainPage extends HttpServlet {

    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();
    private CategoryService categoryService = new CategoryServiceImpl(categoriRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("src/main/resources/html/MainPage.html");

        String html = Files.readString(path);

        StringBuilder stringBuilder = new StringBuilder();
        List<CategoriesEntity> allCategories = categoryService.findAllCategories();

        for (CategoriesEntity categoriesEntity : allCategories) {
            stringBuilder
                    .append("<option value=")
                    .append(categoriesEntity.getId())
                    .append(">")
                    .append(categoriesEntity.getId()).append("</option>");
        }

        String replace = html.replace("<!-- category dropdown -->", stringBuilder);

        resp.getWriter().print(replace);
    }
}
