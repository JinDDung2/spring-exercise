package lieklion.dao;

import lieklion.connection.AwsConnection;
import lieklion.connection.ConnectionMaker;
import lieklion.domain.User;
import org.junit.jupiter.api.BeforeEach;
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
        userDao.save(new User("0", "default", "0000"));/**/
    }

    @Test
    void saveAndFindById() {
        String id = "0";
        userDao.save(new User(id, "zero", "1234"));
        User findUser = userDao.findById(id);
        assertEquals("zero", findUser.getName());
    }


}