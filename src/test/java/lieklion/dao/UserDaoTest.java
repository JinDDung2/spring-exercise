package lieklion.dao;

import lieklion.connection.AwsConnection;
import lieklion.connection.ConnectionMaker;
import lieklion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    UserDao userDao;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        userDao.save(new User("0", "default", "0000"));
    }

    @Test
    @DisplayName("저장과 찾기")
    void saveAndFindById() {
        String id = "11";
        userDao.save(new User(id, "IVE", "0000"));
        User findUser = userDao.findById(id);
        assertEquals("IVE", findUser.getName());
    }

    @Test
    @DisplayName("save할 게 없으면 예외처리")
    void addException() {
        userDao.deleteAll();
        User user = null;
        assertThrows(NullPointerException.class, () -> {
            userDao.save(user);
        });
    }

    @Test
    @DisplayName("find할 수 없으면 예외처리")
    void findByIdEmpty() {
        assertThrows(RuntimeException.class, () -> {
            userDao.findById("1000");
        });

    }

    @Test
    @DisplayName("갯수 세기")
    void getCount() {
        assertEquals(1, userDao.getCount());
        userDao.save(new User("300", "homidle", "1234"));
        assertEquals(2, userDao.getCount());
    }


}