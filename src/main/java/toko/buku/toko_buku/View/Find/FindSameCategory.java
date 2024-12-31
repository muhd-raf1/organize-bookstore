package toko.buku.toko_buku.View.Find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;
import toko.buku.toko_buku.Service.Category.CategoryService;
import toko.buku.toko_buku.Service.Category.CategoryServiceImpl;

@WebServlet(urlPatterns = "/category")
public class FindSameCategory extends HttpServlet {

    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();
    private CategoryService categoryService = new CategoryServiceImpl(categoriRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("src/main/resources/html/Find/FindSameCategory.html");
        String category = req.getParameter("category");
        String htmlCategory = Files.readString(path);

        List<BooksEntity> sameCategory = categoryService.findBooksSameCategory(category);

        if (sameCategory != null) {

            StringBuilder stringBuilder = new StringBuilder();

            for (BooksEntity booksEntity : sameCategory) {
                stringBuilder
                        .append("<tr><th scope=\"row\">")
                        .append(booksEntity.getId())
                        .append("</th><td>").append(booksEntity.getTitle())
                        .append("</td><td>").append(booksEntity.getAuthor()).append("</td><td>")
                        .append(booksEntity.getPublisher()).append("</td>")
                        .append("<td>").append(booksEntity.getIdCategori().getId()).append("</td></tr>");
            }

            String replace = htmlCategory.replace("<!-- Same Cetegory -->", stringBuilder);

            resp.getWriter().print(replace);

        }
    }
}
