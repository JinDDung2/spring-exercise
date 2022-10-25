package lieklion.dao;


import lieklion.connection.AwsConnection;
import lieklion.connection.ConnectionMaker;
import lieklion.domain.User;
import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void save(User user) {
        try {
            Connection conn = connectionMaker.makeConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
            close(conn, ps);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM users WHERE users.id=?";

        try {
            conn = connectionMaker.makeConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));

            close(conn, ps, rs);
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // 가변인자로 받아서, 파라미터를 여러개 받는다.
    private void close(AutoCloseable... autoCloseable) {
        for (AutoCloseable ac : autoCloseable) {
            try {
                ac.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}
