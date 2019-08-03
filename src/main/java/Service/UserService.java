package Service;

import Bean.User;
import Comment.Connecter;
import Comment.KeyUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    Connection cn = null;

    public User registUser(String name, String pwd, int age, String type, String classes) {
        User user = null;
        try {
            cn = new Connecter().getConnetcion();
            String id = KeyUtils.genUniqueKey();
            String sql1 = "insert into user(id,name,pwd,age,type,class) values (?,?,?,?,?,?);";
            PreparedStatement ps1 = cn.prepareStatement(sql1);
            ps1.setString(1, id);
            ps1.setString(2, name);
            ps1.setString(3, pwd);
            ps1.setInt(4, age);
            ps1.setString(5, type);
            ps1.setString(6, classes);
            int rs = ps1.executeUpdate();
            if (rs >= 1) {
                user = new User(id, name, pwd, type, age, classes);
            }
            ps1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public User loginUser(String name, String pwd, String type) {
        User user = null;
        try {
            cn = new Connecter().getConnetcion();
            String sql = "select * from user where name= ? and pwd=? and type=?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pwd);
            ps.setString(3, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setPwd(rs.getString("pwd"));
                user.setType(rs.getString("type"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("登陆失败");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return user;
        }
    }

    public User findByName(String name) {
        User user = null;
        try {
            cn = new Connecter().getConnetcion();
            String sql = "select * from user where name = ?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setAge(rs.getInt("age"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }
}
