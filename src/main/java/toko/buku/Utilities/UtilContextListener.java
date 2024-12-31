package toko.buku.Utilities;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class UtilContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UtilEntityManagerFactoy.getEntityManagerFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        UtilEntityManagerFactoy.closeEntityManagerfactory();
        System.out.println("note : entityManagerFactory closed!");
    }
}
