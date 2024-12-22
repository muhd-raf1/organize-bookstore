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
import toko.buku.toko_buku.Repository.Book.BooksRepository;
import toko.buku.toko_buku.Repository.Book.BooksRepositoryImpl;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;
import toko.buku.toko_buku.Service.Book.BookService;
import toko.buku.toko_buku.Service.Book.BookServiceImpl;
import toko.buku.toko_buku.Service.Category.CategoryService;
import toko.buku.toko_buku.Service.Category.CategoryServiceImpl;

@WebServlet(urlPatterns = "/mainPage")
public class MainPage extends HttpServlet {

    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();
    private CategoryService categoryService = new CategoryServiceImpl(categoriRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Main Page</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
                </head>
                <body>

                    <!-- search title and category -->
                    <form action="/mainPage" method="post">
                        <div class="mb-3 p-5">
                            <label for="exampleFormControlInput1" class="form-label">Book's Title : </label>
                            <input type="text" class="form-control d-flex p-2 border-black" name="bookName" placeholder="example : The Origin of Species">
                        </div>

                        <select name="category" class="form-select form-select-lg mb-3" aria-label="Large select example">
                            <option selected>Open this to select Category</option>
                            <!-- SELECT CATEGORY -->
                        </select>

                        <input class="btn btn-primary" type="submit" value="Submit">
                    </form>
                    <!-- end search title and category -->

                    <form action="/mainPage" method="get"></form>

                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
                </body>
                </html>
                 """;

        List<CategoriesEntity> allCategories = categoryService.findAllCategories();

        StringBuilder dynamicHtml = new StringBuilder();
        for (CategoriesEntity categories : allCategories) {
            dynamicHtml.append("<option>").append(categories.getId()).append("</option>");
        }

        // replace '<!-- SELECT CATEGORY -->' menjadi code dynamic (code yg kita
        // inginkan)
        String finalHtml = html.replace("<!-- SELECT CATEGORY -->", dynamicHtml.toString());

        resp.getWriter().println(finalHtml);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String category = req.getParameter("category");
    }
}
