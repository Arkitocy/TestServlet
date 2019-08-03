package Test;

import java.sql.*;

public class JDBCtest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/zzty-cy?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String user = "zztyuser";
        String password = "114514";
        Connection connection = null;
        try {
            //现版本不要求通过此方法注册驱动，但可以使用
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password); //设置连接数据库配置 url为数据库地址以及连接参数 user为用户 password为密码
            PreparedStatement st = connection.prepareStatement("select student_id,student_name from student ");//通过preparedstatement执行sql语句
            ResultSet rs = st.executeQuery();//返回内容存在resultset中
            while (rs.next()) {
                System.out.print("学生id：" + rs.getInt("student_id"));
                System.out.print("  学生姓名：" + rs.getString("student_name"));

            }
            rs.close();
            st.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
