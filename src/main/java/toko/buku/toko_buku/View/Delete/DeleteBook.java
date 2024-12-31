package toko.buku.toko_buku.View.Delete;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;
import toko.buku.toko_buku.Repository.Book.BooksRepositoryImpl;
import toko.buku.toko_buku.Service.Book.BookService;
import toko.buku.toko_buku.Service.Book.BookServiceImpl;

@WebServlet(urlPatterns = "/deleteBook")
public class DeleteBook extends HttpServlet {

    private BooksRepository booksRepository = new BooksRepositoryImpl();
    private BookService bookService = new BookServiceImpl(booksRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idBook = req.getParameter("bookDelete");
        BooksEntity byIdOrTitle = bookService.findByIdOrTitle(idBook);

        if (byIdOrTitle != null) {
            boolean delete = bookService.delete(byIdOrTitle.getId());

            if (!delete) {
                String alert = """
                        <script>
                            alert('Fail to delete');
                        </script>
                        """;
                resp.getWriter().println(alert);
            } else {
                resp.sendRedirect("/mainPage");
            }
        } else {
            String alert = """
                    <script>
                        alert('Not Found')
                    </script>
                    """;
            resp.getWriter().println(alert);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("src/main/resources/html/Delete/DeleteBook.html");

        String deleteHtml = Files.readString(path);

        resp.getWriter().print(deleteHtml);
    }
}
