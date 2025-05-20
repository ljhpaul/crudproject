package com.shinhan.job3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil 클래스는 데이터베이스 연결 및 해제를 처리합니다.
 */
public class DBUtil {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle DB URL
    private static final String USER = "hr"; // DB 사용자 이름
    private static final String PASSWORD = "hr"; // DB 비밀번호

    /**
     * 데이터베이스 연결을 생성합니다.
     * 
     * @return Connection 객체
     * @throws SQLException 데이터베이스 연결 실패 시 예외 발생
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 데이터베이스 연결을 해제합니다.
     * 
     * @param conn Connection 객체
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
