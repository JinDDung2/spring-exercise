package lieklion.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static lieklion.connection.ConnectionConst.*;

public class AwsConnection implements ConnectionMaker {
    @Override
    public Connection makeConnection() {
        Map<String, String> env = System.getenv();
        try {
            Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
