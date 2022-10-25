package lieklion.connection;

import java.util.Map;

public class ConnectionConst {
    public static Map<String, String> env = System.getenv();
    public static final String dbHost = env.get("DB_HOST");
    public static final String dbUser = env.get("DB_USER");
    public static final String dbPassword = env.get("DB_PASSWORD");

}
