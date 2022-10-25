package lieklion.dao;

import lieklion.connection.AwsConnection;
import lieklion.connection.ConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao() {
        return new UserDao(new AwsConnection());
    }

}
