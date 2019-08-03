package Service;

import Bean.Toy;
import Comment.Connecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToyService {
    Connecter connecter = new Connecter();
    Connection cn = null;

    public ArrayList<Toy> showAll() {
        ArrayList<Toy> ap = new ArrayList();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from toy ;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Toy t1 = new Toy();
                t1.setId(rs.getString("id"));
                t1.setName(rs.getString("name"));
                t1.setAmount(rs.getInt("amount"));
                ap.add(t1);
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
            return ap;

        }

    }
}
