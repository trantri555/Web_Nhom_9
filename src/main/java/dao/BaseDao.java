package dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;

public class BaseDao {
    private Jdbi jdbi;

    public Jdbi get() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    private void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.getURL("jdbi:mysql://" + DBproperties.getDbHost() + ":" + DBproperties.getDbPort() + "/"
                + DBproperties.getDbName());
        dataSource.setUser(DBproperties.getUsername());
        dataSource.setPassword(DBproperties.getPassword());
        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
        jdbi = jdbi.create(dataSource);
    }
}
