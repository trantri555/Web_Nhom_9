//package dao;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//import model.Product;
//import org.jdbi.v3.core.Jdbi;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public abstract class BaseDao {
//    private Jdbi jdbi;
//
//    public Jdbi get() {
//        if (jdbi == null) makeConnect();
//        return jdbi;
//    }
//
//    private void makeConnect() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.getURL("jdbi:mysql://" + DBproperties.getDbHost() + ":" + DBproperties.getDbPort() + "/"
//        + DBproperties.getDbName());
//        dataSource.setUser(DBproperties.getUsername());
//        dataSource.setPassword(DBproperties.getPassword());
//        try {
//            dataSource.setUseCompression(true);
//            dataSource.setAutoReconnect(true);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException(throwables);
//        }
//        jdbi = jdbi.create(dataSource);
//    }
//
////    public static void main(String[] args) {
////        BaseDao bs = new BaseDao();
////        Jdbi jdbi = bs.get();
////        List<Product> products = jdbi.withHandle(h -> {
////            return h.createQuery("select * from products").mapToBean(Product.class).list();
////        });
////        System.out.println(products);
////    }
//}
package dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;
import java.sql.SQLException;

public class BaseDao { // Bỏ abstract để có thể test hoặc để nguyên nếu chỉ dùng để kế thừa
    private Jdbi jdbi;

    public Jdbi get() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    private void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        String url = "jdbc:mysql://" + DBproperties.getDbHost() + ":" + DBproperties.getDbPort() + "/" + DBproperties.getDbName();
        dataSource.setURL(url);
        dataSource.setUser(DBproperties.getUsername());
        dataSource.setPassword(DBproperties.getPassword());

        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
            dataSource.setServerTimezone("UTC");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        jdbi = Jdbi.create(dataSource);

        // QUAN TRỌNG: Hãy bỏ comment dòng này nếu bạn đã add dependency jdbi3-sqlobject vào pom.xml
        // jdbi.installPlugin(new org.jdbi.v3.sqlobject.SqlObjectPlugin());
    }
    public static void main(String[] args) {
        try {
            BaseDao dao = new BaseDao();
            dao.get().withHandle(h -> {
                System.out.println("Chúc mừng! Kết nối Navicat/Wamp thành công!");
                return null;
            });
        } catch (Exception e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
            e.printStackTrace();
        }
    }
}