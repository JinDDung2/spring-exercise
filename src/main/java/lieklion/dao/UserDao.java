package lieklion.dao;


import lieklion.connection.AwsConnection;
import lieklion.connection.ConnectionMaker;
import lieklion.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao {
    /*private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }*/

    /*private DataSource dataSource;
    private JdbcContext jdbcContext;*/
    private final JdbcTemplate template;

    public UserDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        String sql = "INSERT INTO users(id, name, password) VALUES(?, ?, ?)";
        template.update(sql, user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id) {
        String sql = "SELECT * FROM users where users.id=?";
        return template.queryForObject(sql, userRowMapper(), id);
    }

    private RowMapper<User> userRowMapper() {
        return ((rs, rowNum) -> {
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            return user;
        });
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users ORDER BY id";
        return template.query(sql, userRowMapper());
    }


    public void deleteAll() {
        String sql = "DELETE FROM users";
        template.update(sql);
    }

    public int getCount() {
        String sql = "SELECT COUNT(*) FROM users";
        return template.queryForObject(sql, Integer.class);
    }


    // 가변인자로 받아서, 파라미터를 여러개 받는다.
    private void close(AutoCloseable... autoCloseable) {
        for (AutoCloseable ac : autoCloseable) {
            if (ac != null) {
                try {
                    ac.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
