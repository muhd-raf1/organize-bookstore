package toko.buku.toko_buku.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;
import toko.buku.toko_buku.Repository.Book.BooksRepositoryImpl;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;
import toko.buku.toko_buku.Service.Book.BookService;
import toko.buku.toko_buku.Service.Book.BookServiceImpl;
import toko.buku.toko_buku.Service.Category.CategoryService;
import toko.buku.toko_buku.Service.Category.CategoryServiceImpl;

@WebServlet(urlPatterns = "/addBook")
public class AddBook extends HttpServlet {

    private BooksRepository booksRepository = new BooksRepositoryImpl();
    private BookService bookService = new BookServiceImpl(booksRepository);
    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();
    private CategoryService categoryService = new CategoryServiceImpl(categoriRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("src/main/resources/html/AddBook.html");
        String addBookHtml = Files.readString(path);

        StringBuilder stringBuilder = new StringBuilder();

        List<CategoriesEntity> allCategories = categoryService.findAllCategories();

        for (CategoriesEntity category : allCategories) {

            stringBuilder.append("<option value=").append(category.getId())
                    .append(">").append(category.getId()).append("</option>");
        }

        String replace = addBookHtml.replace("<!-- All Category -->", stringBuilder);

        resp.getWriter().print(replace);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String category = req.getParameter("category");
        String yearOfParameter = req.getParameter("yearOfPublish");
        String description = req.getParameter("description");
        String pages = req.getParameter("pages");

        BooksEntity book = new BooksEntity();
        book.setAuthor(author);
        book.setCreatedAt(LocalDateTime.now());
        book.setDescription(description);
        book.setId(id);
        book.setIdCategori(new CategoriesEntity(category));

        if (pages != null) {
            book.setPages(Integer.parseInt(pages));
        } else {
            book.setPages(null);
        }

        book.setPublisher(publisher);
        book.setTitle(title);

        if (yearOfParameter != null) {
            book.setYearOfPublish(LocalDateTime.parse(yearOfParameter));
        } else {
            String alert = "<script>alert('year of publish " + yearOfParameter + "')</script>";
            resp.getWriter().print(alert);
        }

        boolean resultBook = bookService.add(book);

        if (resultBook != true) {
            resp.sendRedirect(req.getRequestURI());

        } else {
            resp.sendRedirect("/mainPage");
        }
    }
}
