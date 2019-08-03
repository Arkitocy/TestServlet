package Service;

import Bean.Student;
import Comment.Connecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {
    Connection cn;
    {
        try {
            cn = new Connecter().getConnetcion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean insertStudent(Student S) {
        boolean f = false;
        try {

            String sql1 = "insert into student(student_name,student_age,student_sex,team_id) values (?,?,?,?);";
            PreparedStatement ps1 = cn.prepareStatement(sql1);
            ps1.setString(1, S.getStudent_name());
            ps1.setInt(2, S.getStudent_age());
            ps1.setString(3, S.getStudent_sex());
            ps1.setInt(4, S.getTeam_id());
            int rs = ps1.executeUpdate();
            if (rs >= 1) {
                f = true;
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
            return f;
        }
    }

    public boolean showStudent(String sn) {
        boolean f = false;
        try {

            String sql = "select * from student where student_name= ?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, sn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.print("学生id：" + rs.getInt("student_id"));
                System.out.print("  学生姓名：" + rs.getString("student_name"));
                System.out.print("  学生性别：" + rs.getString("student_sex"));
                System.out.println("    学生年龄：" + rs.getString("student_age"));
            }
            rs.close();
            ps.close();
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
            return f;

        }
    }

    public boolean deleteStudent(String sn) {
        boolean f = false;
        try {
            String sql = "delete from student where student_name=?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, sn);
            int rs = ps.executeUpdate();
            if (rs >= 1) {
                f = true;
            }
            ps.close();
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
            return f;
        }
    }

    public boolean updateStudent(String sn, Student S) {
        boolean f = false;
        try {
            String sql1 = "update  student set student_name = ?,student_age=?,student_sex =?,team_id =? where student_name=?;";
            PreparedStatement ps1 = cn.prepareStatement(sql1);
            ps1.setString(1, S.getStudent_name());
            ps1.setInt(2, S.getStudent_age());
            ps1.setString(3, S.getStudent_sex());
            ps1.setInt(4, S.getTeam_id());
            ps1.setString(5, sn);
            int rs = ps1.executeUpdate();
            if (rs >= 1) {
                f = true;
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
            return f;
        }
    }
}