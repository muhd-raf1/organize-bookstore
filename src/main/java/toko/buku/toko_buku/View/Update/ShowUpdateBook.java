package toko.buku.toko_buku.View.Update;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;
import toko.buku.toko_buku.Repository.Book.BooksRepositoryImpl;
import toko.buku.toko_buku.Service.Book.BookService;
import toko.buku.toko_buku.Service.Book.BookServiceImpl;

@WebServlet(urlPatterns = "/showUpdateBook")
public class ShowUpdateBook extends HttpServlet {

    private BooksRepository booksRepository = new BooksRepositoryImpl();
    private BookService bookService = new BookServiceImpl(booksRepository);
    private BooksEntity book;
    private String idBook;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("src/main/resources/html/Update/showUpdateBook.html");
        String html = Files.readString(path);
        String bookValue = req.getParameter("book");
        book = bookService.findByIdOrTitle(bookValue);
        idBook = bookValue;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<form action=\"/showUpdateBook\" method=\"post\">")
                .append("<table class=\"table position-absolute top-50 start-50 translate-middle h-auto w-75 table-bordered border-black\"><tbody>")
                .append("<tr>")
                .append("<td>Genre</td>")
                .append("<td><input type='text' placeholder=").append(book.getIdCategori().getId())
                .append(" name='idCategory' class='border border-opacity-10 form-control' id='exampleFormControlInput1'>")
                .append(" </td></tr>")
                .append("<tr>")
                .append("<td>Title</td>")
                .append("<td><input type='text' placeholder=").append(book.getTitle().replace(" ", "-"))
                .append(" name='title' class='border border-opacity-10 form-control' id='exampleFormControlInput1'>")
                .append(" </td></tr>")
                .append("<tr>")
                .append("<td>Author</td>")
                .append("<td><input type='text' placeholder=").append(book.getAuthor().replace(" ", "-"))
                .append(" name='author' class='border border-opacity-10 form-control'id='exampleFormControlInput1'>")
                .append(" </td></tr>")
                .append("<tr>")
                .append("<td>Publisher</td>")
                .append("<td><input type='text' placeholder=").append(book.getPublisher().replace(" ", "-"))
                .append(" name='publisher' class='border border-opacity-10 form-control'id='exampleFormControlInput1'>")
                .append(" </td></tr>")
                .append("<tr>")
                .append("<td>Synopsis</td>")
                .append("<td><textarea type='text' name='description' class='border border-opacity-10 form-control'id='exampleFormControlInput1' style='height: 100px'>")
                .append(book.getDescription()).append("</textarea>")
                .append(" </td></tr>")
                .append("<tr>")
                .append("<tr>")
                .append("<td>Pages</td>")
                .append("<td><input type='number' value=").append(book.getPages())
                .append(" name='pages' class='border border-opacity-10 form-control' id='exampleFormControlInput1'>")
                .append(" </td></tr>")
                .append("<td>Year of Publish</td>")
                .append("<td><input class='disabled' type='datetime-local' value=").append(book.getYearOfPublish())
                .append(" name='yearOfPublish' class='border border-opacity-10 form-control'id='datetime'>")
                .append(" </td></tr>")
                .append("  </tbody></table>")
                .append("<div class='position-absolute bottom-0 start-50 translate-middle'><button class=\"btn btn-primary\" type=\"submit\">Update</button></div>")
                .append("</form>");

        String finalHtml = html.replace("<!-- Book Update Detail -->", stringBuilder.toString());
        resp.getWriter().print(finalHtml);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCategory = req.getParameter("idCategory");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String description = req.getParameter("description");
        String pages = req.getParameter("pages");
        String yearOfPublish = req.getParameter("yearOfPublish");

        // Convert dari string -> localDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(yearOfPublish);
        // Convert from String -> Integer
        int pagesInt = Integer.parseInt(pages);

        BooksEntity byIdOrTitle = bookService.findByIdOrTitle(idBook);

        book = new BooksEntity();
        book.setAuthor(author);
        book.setDescription(description);
        book.setIdCategori(new CategoriesEntity(idCategory));
        book.setPages(pagesInt);
        book.setPublisher(publisher);
        book.setTitle(title);
        book.setYearOfPublish(localDateTime);

        boolean update = bookService.update(byIdOrTitle.getId(), book);

        if (update) {
            String alert = """
                    <script>
                        alert("Success Updated");
                    </script>
                    """;
            resp.sendRedirect("/mainPage");
            resp.getWriter().println(alert);

        } else {
            String alert = """
                    <script>
                        alert("Fail Updated !");
                    </script>
                    """;
            resp.getWriter().println(alert);
        }
    }
}
