package Test;

import java.sql.*;

public class Test2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/zzty-cy?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String user = "zztyuser";
        String password = "114514";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
//
            String sql1 = "insert into user(name,pwd,age) values ('小张','837',24);";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.executeUpdate();
            String sql2 = "select * from user;";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("pwd"));
            }
            rs.close();
            ps1.close();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}