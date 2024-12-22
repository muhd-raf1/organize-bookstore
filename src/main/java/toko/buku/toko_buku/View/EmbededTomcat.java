package toko.buku.toko_buku.View;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class EmbededTomcat {

    public static void main(String[] args) {
        SpringApplication.run(EmbededTomcat.class, args);
    }
}
