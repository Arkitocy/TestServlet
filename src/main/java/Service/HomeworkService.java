package Service;

import Bean.HomeWorkRank;
import Bean.Homework;
import Bean.HomeworkDetail;
import Bean.HwDetail;
import Comment.Connecter;
import Comment.KeyUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class HomeworkService {
    Connecter connecter = new Connecter();
    Connection cn = null;

    public boolean addHomework(Homework homework) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql = "insert into homework (id,title,type,finish_time) values (?,?,?,?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, homework.getId());
            ps.setString(2, homework.getTitle());
            ps.setString(3, homework.getType());
            ps.setTimestamp(4, new Timestamp(homework.getFinish_time().getTime()));
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
        }
        return f;
    }


    public boolean addHomework_User(String hid, String uid) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql = "insert into user_homework (h_id,u_id,complete_time,status) values (?,?,?,?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, hid);
            ps.setString(2, uid);
            ps.setTimestamp(3, new Timestamp(new Date().getTime()));
            ps.setString(4, "完成");

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
        }
        return f;
    }


    public boolean updateHomeworkTime(String id) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql2 = "update homework set update_time=? where id=?";
            PreparedStatement ps2 = cn.prepareStatement(sql2);
            ps2.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps2.setString(2, id);
            int rs = ps2.executeUpdate();
            if (rs >= 1) {
                f = true;
            }
            ps2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean addDetail(String id, ArrayList<String> details) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            for (int i = 0; i < details.size(); i++) {
                String sql2 = "insert into homework_detail(id,h_id,detail)  values (?,?,?);";
                PreparedStatement ps2 = cn.prepareStatement(sql2);
                ps2.setString(1, KeyUtils.genUniqueKey());
                ps2.setString(2, id);
                ps2.setString(3, details.get(i));
                int rs = ps2.executeUpdate();
                boolean f2 = updateHomeworkTime(id);
                if ((rs >= 1) && f2) {
                    f = true;
                }
                ps2.close();
            }
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
        return f;
    }

    public ArrayList<Homework> getHomework(){
        ArrayList<Homework> homeworkArrayList = new ArrayList<>();
        Homework p1 = null;
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from homework ;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p1 = new Homework();
                p1.setId(rs.getString("id"));
                p1.setTitle(rs.getString("title"));
                p1.setType(rs.getString("type"));
                p1.setUpdate_time(rs.getTimestamp("update_time"));
                p1.setFinish_time(rs.getTimestamp("finish_time"));
                homeworkArrayList.add(p1);
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
            return homeworkArrayList;

        }
    }


    public ArrayList<Homework> getHomeworkByDateAndType(Date date, String type) {
        ArrayList<Homework> homeworkArrayList = new ArrayList<>();
        Homework p1 = null;
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from homework where date_format(finish_time,'%Y-%m-%d')=date_format(?,'%Y-%m-%d') and type =?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(date.getTime()));
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p1 = new Homework();
                p1.setId(rs.getString("id"));
                p1.setTitle(rs.getString("title"));
                p1.setType(rs.getString("type"));
                p1.setUpdate_time(rs.getTimestamp("update_time"));
                p1.setFinish_time(rs.getTimestamp("finish_time"));
                homeworkArrayList.add(p1);
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
            return homeworkArrayList;

        }

    }

    public Homework getHomeworkById(String id) {
        Homework p1 = null;
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from homework where id =?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p1 = new Homework();
                p1.setId(rs.getString("id"));
                p1.setTitle(rs.getString("title"));
                p1.setType(rs.getString("type"));
                p1.setUpdate_time(rs.getTimestamp("update_time"));
                p1.setFinish_time(rs.getTimestamp("finish_time"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p1;


    }


    public ArrayList<String> showDetails(Homework homework) {
        ArrayList<String> a = new ArrayList<>();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from homework_detail where h_id = ? ;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, homework.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a.add(rs.getString("detail"));
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
            return a;
        }
    }

    public boolean addStatus(String str, String hid, String uid) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql = "update  user_homework  set status = ? where h_id=? and u_id=?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, str);
            ps.setString(2, hid);
            ps.setString(3, uid);
            int rs = ps.executeUpdate();
            if (rs >= 1) {
                f = true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public ArrayList<HomeworkDetail> showByHid(Homework hw) {
        ArrayList<HomeworkDetail> a = new ArrayList<>();
        try {
            cn = connecter.getConnetcion();
            String sql = "SELECT * from (SELECT u.id,u.type,u.name,u.age,(case when h.id  is null then ? else h.id end) AS hid,(case when h.title  is null then ? else h.title end) AS htitle,(case when h.type is null then ? else h.type end) AS htype,(case when h.finish_time is null then ? else h.finish_time end) AS hfinish_time, uk.complete_time FROM `user` u LEFT JOIN user_homework  uk LEFT JOIN homework  h ON uk.h_id=h.id ON u.id=uk.u_id WHERE h.id=? OR h.id is null) as a where a.type=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, hw.getId());
            ps.setString(2, hw.getTitle());
            ps.setString(3, hw.getType());
            ps.setTimestamp(4, new Timestamp(hw.getFinish_time().getTime()));
            ps.setString(5, hw.getId());
            ps.setString(6,"student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HomeworkDetail p1 = new HomeworkDetail();
                p1.setId(rs.getString("id"));
                p1.setName(rs.getString("name"));
                p1.setAge(rs.getString("age"));
                p1.setHid(rs.getString("hid"));
                p1.setTitle(rs.getString("htitle"));
                p1.setType(rs.getString("htype"));
                p1.setFinish_time(rs.getTimestamp("hfinish_time"));
                p1.setComplete_time(rs.getTimestamp("complete_time"));
                if (p1.getComplete_time() == null) {
                    p1.setStatus("未完成");
                    this.addStatus("未完成", rs.getString("hid"), rs.getString("id"));
                } else {
                    p1.setStatus("完成");
                    this.addStatus("完成", rs.getString("hid"), rs.getString("id"));
                }
                a.add(p1);

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
            return a;
        }
    }


    public ArrayList<HomeWorkRank> getRank(String type) {
        ArrayList<HomeWorkRank> ahr = new ArrayList<>();
        if (type.equals("全部")) {
            try {
                cn = connecter.getConnetcion();
                String sql = "SELECT `user`.`name`,COUNT(user_homework.`status`) ac FROM `user` LEFT JOIN user_homework ON `user`.id=user_homework.u_id GROUP BY `user`.id";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    HomeWorkRank hr = new HomeWorkRank();
                    hr.setName(rs.getString("name"));
                    hr.setAc(rs.getInt("ac"));
                    ahr.add(hr);

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
                return ahr;
            }
        } else {
            try {
                cn = connecter.getConnetcion();
                String sql = "\n" +
                        "SELECT `user`.`name`,count(t.h_id) ac FROM `user` LEFT JOIN (SELECT `user`.id,`user`.`name`,user_homework.h_id FROM `user` LEFT JOIN user_homework on `user`.id = user_homework.u_id LEFT JOIN homework on user_homework.h_id=homework.id where homework.type=?) t on t.id = `user`.id GROUP BY `user`.id ORDER BY COUNT(t.h_id) desc;";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, type);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    HomeWorkRank hr = new HomeWorkRank();
                    hr.setName(rs.getString("name"));
                    hr.setAc(rs.getInt("ac"));
                    ahr.add(hr);

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
                return ahr;
            }
        }


    }

    public ArrayList<HwDetail> showHwDetails(Homework homework){
        ArrayList<HwDetail> a = new ArrayList<>();
        HwDetail hw = new HwDetail();
        try {
            cn = connecter.getConnetcion();
            String sql = "select homework_detail.h_id hid, homework.title hname, homework_detail.detail details from homework_detail LEFT JOIN  homework ON homework_detail.h_id=homework.id HAVING homework_detail.h_id=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, homework.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hw.setId(rs.getString("hid"));
                hw.setName(rs.getString("hname"));
                hw.setDetail(rs.getString("details"));
                a.add(hw);
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
            return a;
        }

    }

}
