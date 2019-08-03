package Service;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ParkService {
    String url = "jdbc:mysql://localhost:3306/zzty-cy?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    String user = "root";
    String password = "114514";
    Connection conn = null;

    public void in(String carNo, java.util.Date intime) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            Timestamp timeStamp = new Timestamp(intime.getTime());
            String sql1 = "insert into park values (?,?,?,0 );";

            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, carNo);
            ps1.setTimestamp(2, timeStamp);
            ps1.setDate(3, null);
            ps1.executeUpdate();
            ps1.close();

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

    public void out(String carNo, java.util.Date outtime) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            float price = 0;
            String sql1 = "select inTime from park where carNo=?;";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, carNo);
            String sql2 = "delete from park where carNo=?;";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            String sql3 = "update park set outTime = ? where carNo = ? ";
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            String sql4 = "update park set price = ? where carNo = ? ";
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ResultSet rs = ps1.executeQuery();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
            while (rs.next()) {
                java.util.Date rsDate = rs.getTimestamp("inTime");
                java.util.Date parse = format.parse(format.format(rsDate));
                java.util.Date date = format.parse(format.format(outtime));
                long between = date.getTime() - parse.getTime();
                long day = between / (24 * 60 * 60 * 1000);
                long hour = (between / (60 * 60 * 1000) - day * 24);
                long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
                long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒");
                if (day > 1) {
                    price = 300;
                } else if (hour > 1) {
                    price = (hour - 1) * 10;
                } else {
                    price = 0;
                }
                ps4.setFloat(1, price);
                ps4.setString(2, carNo);
                ps4.executeUpdate();
            }
            Timestamp timeStamp = new Timestamp(outtime.getTime());
            ps3.setTimestamp(1, timeStamp);
            ps3.setString(2, carNo);
            ps3.executeUpdate();
            ps2.setString(1, carNo);
//            ps2.executeUpdate();
            ps1.close();
            ps2.close();
            ps3.close();
            ps4.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

