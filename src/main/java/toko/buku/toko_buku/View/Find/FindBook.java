package toko.buku.toko_buku.View.Find;

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

@WebServlet(urlPatterns = "/findBook")
public class FindBook extends HttpServlet {

    private BooksRepository booksRepository = new BooksRepositoryImpl();
    private BookService bookService = new BookServiceImpl(booksRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String book = req.getParameter("book");

        BooksEntity bookResult = bookService.findByIdOrTitle(book);

        if (bookResult != null) {
            Path path = Path.of("src/main/resources/html/Find/FindBook.html");
            String htmlFindBook = Files.readString(path);

            StringBuilder dynamicHtml = new StringBuilder();

            dynamicHtml.append("<h5 class='card-title text-center'>").append(bookResult.getTitle()).append("</h5>")
                    .append("<p class='card-text text-center'>").append(bookResult.getDescription()).append("</p>")
                    .append("<br>")
                    .append("<div class = 'ms-4'>")
                    .append("<p class='card-text' >").append("Author : " + bookResult.getAuthor()).append("</p>")
                    .append("<p class='text-start' >").append("Publisher : " + bookResult.getPublisher()).append("</p>")
                    .append("<p class='text-start' >").append("Genre : " + bookResult.getIdCategori().getId())
                    .append("</p>")
                    .append("<p class='text-start'>").append("Year of Publish : " + bookResult.getYearOfPublish())
                    .append("</p>")
                    .append("<p class='text-start'>").append("Pages : " + bookResult.getPages()).append("</p>")
                    .append("<p class='text-start'>").append("Create : " + bookResult.getCreatedAt()).append("</p>")
                    .append("<p class='text-start'>").append("Last Update : " + bookResult.getUpdatedAt()).append("</p")
                    .append("</div>");

            String finalHtml = htmlFindBook.replace("<!-- Book -->", dynamicHtml.toString());
            resp.getWriter().print(finalHtml);
        }
    }
}
