package Service;

import Bean.Order;
import Comment.Connecter;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class OrderService {
    Connecter connecter = new Connecter();
    Connection cn = null;

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    public boolean addOrder(Order order) {
        boolean f = false;
        if (this.findByName(order.getName()) != true) {
            try {
                cn = connecter.getConnetcion();
                String sql = "insert into order1(name,ordertime) values (?,?);";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, order.getName());
                ps.setTimestamp(2, new Timestamp(order.getOrdertime().getTime()));
                int rs = ps.executeUpdate();
                if (rs >= 1) {
                    f = true;
                }
                int id = 0;
                String sql2 = "SELECT LAST_INSERT_ID();";
                PreparedStatement ps2 = cn.prepareStatement(sql2);
                ResultSet s = ps2.executeQuery();
                while (s.next()) {
                    id = s.getInt("LAST_INSERT_ID()");
                }
                for (int i = 0; i < order.getArrayList().size(); i++) {
                    this.setOrderProduct(id, order.getArrayList().get(i));
                }
                BigDecimal sum = this.sumPirce(id);
                this.setSumPrice(sum, order.getName());
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

        }
        return f;
    }

    /**
     * 设置Order_Product表
     *
     * @param oid
     * @param pid
     * @return
     */
    public boolean setOrderProduct(int oid, int pid) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql2 = "insert into order_product  values (?,?);";
            PreparedStatement ps2 = cn.prepareStatement(sql2);
            ps2.setInt(1, oid);
            ps2.setInt(2, pid);
            int rs = ps2.executeUpdate();
            if (rs >= 1) {
                f = true;
            }
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 计算总价
     *
     * @param id
     * @return
     */
    public BigDecimal sumPirce(int id) {
        BigDecimal sum = new BigDecimal(0);
        try {
            cn = connecter.getConnetcion();
            String sql6 = "select * from product where id in(select p_id from order_product where o_id = ?) ;";
            PreparedStatement ps6 = cn.prepareStatement(sql6);
            ps6.setInt(1, id);
            ResultSet rs = ps6.executeQuery();
            while (rs.next()) {
                sum = sum.add(rs.getBigDecimal("price"));
            }
            ps6.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }


    /**
     * 设置总价
     *
     * @param sum
     * @param name
     * @return
     */
    public boolean setSumPrice(BigDecimal sum, String name) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql2 = "update order1 set sumprice=? where name = ?;";
            PreparedStatement ps3 = cn.prepareStatement(sql2);
            ps3.setBigDecimal(1, sum);
            ps3.setString(2, name);
            int rs = ps3.executeUpdate();
            if (rs >= 1) {
                f = true;
            }
            ps3.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    public boolean deleteOrder(int id) {
        boolean f = false;
        try {
            this.deleteOrder_Product(id);
            cn = connecter.getConnetcion();
            String sql1 = "delete from order1 where id=?";
            PreparedStatement st1 = cn.prepareStatement(sql1);
            st1.setInt(1, id);
            if (st1.execute()) {
                f = true;
            }
            st1.close();
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

    /**
     * 删除Order_Product表中的订单数据
     *
     * @param id
     * @return
     */
    public boolean deleteOrder_Product(int id) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql2 = "delete from order_product where o_id =?";
            PreparedStatement st2 = cn.prepareStatement(sql2);
            st2.setInt(1, id);
            if (st2.execute()) {
                f = true;
            }
            st2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }


    /**
     * 得到订单
     *
     * @param id
     * @return
     */
    public Order getOrder(int id) {
        Order order = new Order();
        ArrayList arrayList = new ArrayList();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from order1 o left join order_product op on (o.id=op.o_id) where o.id=?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order.setId(id);
                order.setName(rs.getString("name"));
                order.setOrdertime(rs.getTimestamp("ordertime"));
                order.setSumprice(rs.getBigDecimal("sumprice"));
                arrayList.add(rs.getInt("p_id"));
            }
            order.setArrayList(arrayList);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    /**
     * 升级订单
     *
     * @param id
     * @param order
     * @return
     */
    public boolean updateOrder(int id, Order order) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql1 = "update order1 set name=?,ordertime=? where id=?";
            PreparedStatement st1 = cn.prepareStatement(sql1);
            st1.setString(1, order.getName());
            st1.setTimestamp(2, new Timestamp(order.getOrdertime().getTime()));
            st1.setInt(3, id);
            int rs = st1.executeUpdate();
            if (rs >= 1) {
                f = true;
            }
            this.deleteOrder_Product(id);
            for (int i = 0; i < order.getArrayList().size(); i++) {
                this.setOrderProduct(id, order.getArrayList().get(i));
            }
            BigDecimal sum = this.sumPirce(id);
            this.setSumPrice(sum, order.getName());
            st1.close();
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

    /**
     * 按订单名查找
     *
     * @param name
     * @return
     */
    public boolean findByName(String name) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from order1 where name =?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;

    }
}