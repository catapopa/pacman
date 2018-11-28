package constants;

public class Constants {
    public static final int cellSize = 60;

    public enum Direction {UP, DOWN, LEFT, RIGHT}

    public enum SpeedOption {SLOW, MEDIUM, FAST}

    public static final String DB_URL = "jdbc:mariadb://localhost:3306/pacman";
    public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

    public static final String DatabaseUser = "root";
    public static final String DatabasePassword = "123";
}
