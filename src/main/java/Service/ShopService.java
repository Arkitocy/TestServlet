package Service;


import Bean.Shop;
import Comment.Connecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopService {
    Connecter connecter = new Connecter();
    Connection cn = null;

    /**
     * 添加商店
     *
     * @param shop
     * @return
     */
    public boolean addShop(Shop shop) {
        boolean f = false;
        if (this.findByName(shop.getName()) != null) {
            try {
                cn = connecter.getConnetcion();
                String sql = "insert into shop(name) values (?);";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, shop.getName());
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

        }else {
            System.out.println("商店不存在");
        }
        return f;
    }

    /**
     * 按照名字查找商店
     *
     * @param name
     * @return
     */
    public Shop findByName(String name) {
        Shop shop = new Shop();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from shop where name =?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shop.setId(rs.getInt("id"));
                shop.setName(rs.getString("name"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;

    }


    public boolean findExist(String name, int p_id) {
        boolean f = false;
        int s_id = this.findByName(name).getId();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from shop_product where s_id =? and p_id=?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, s_id);
            ps.setInt(2, p_id);
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


    /**
     * 添加商品及库存
     *
     * @param name
     * @param p_id
     * @param storage
     * @return
     */
    public boolean addProduct(String name, int p_id, int storage) {
        boolean f = false;
        int s_id = this.findByName(name).getId();
        try {
            cn = connecter.getConnetcion();
            if (!(this.findExist(name, p_id))) {
                String sql2 = "insert into shop_product  values (?,?,?);";
                PreparedStatement ps2 = cn.prepareStatement(sql2);
                ps2.setInt(1, s_id);
                ps2.setInt(2, p_id);
                ps2.setInt(3, storage);
                int rs = ps2.executeUpdate();
                if (rs >= 1) {
                    f = true;
                }
                ps2.close();
            }else {
                String sql3 ="update shop_product set storage=? where s_id=? and p_id=?";
                PreparedStatement ps3 = cn.prepareStatement(sql3);
                ps3.setInt(1,this.getStorage(name,p_id)+storage);
                ps3.setInt(2,s_id);
                ps3.setInt(3,p_id);
                int rs = ps3.executeUpdate();
                if (rs >= 1) {
                    f = true;
                }
                ps3.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;

    }

    /**
     * 删除整列库存
     *
     * @param name
     * @param p_id
     * @return
     */
    public boolean deleteProduct(String name, int p_id) {
        boolean f = false;
        try {
            cn = connecter.getConnetcion();
            int s_id = this.findByName(name).getId();
            String sql1 = "delete from shop_product where p_id=? and s_id=? ";
            PreparedStatement st1 = cn.prepareStatement(sql1);
            st1.setInt(1, p_id);
            st1.setInt(2, s_id);
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


    public boolean soldProduct(String name, int p_id, int sum) {
        boolean f = false;
        if (sumEnough(name, p_id, sum)) {
            try {
                cn = connecter.getConnetcion();
                int s_id = this.findByName(name).getId();
                int storage = this.getStorage(name, p_id);
                if(storage==sum){
                    this.deleteProduct(name,p_id);
                }else {
                    String sql1 = "update shop_product set storage=? where s_id=? and p_id=?";
                    PreparedStatement st1 = cn.prepareStatement(sql1);
                    st1.setInt(1, storage - sum);
                    st1.setInt(2, s_id);
                    st1.setInt(3, p_id);
                    int rs = st1.executeUpdate();
                    if (rs >= 1) {
                        f = true;
                    }
                    st1.close();
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
        } else {
            System.out.println("库存不足");
        }
        return f;
    }

    public boolean sumEnough(String name, int p_id, int sum) {
        boolean f = false;
        int s_id = this.findByName(name).getId();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from shop_product where s_id =? and p_id=?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, s_id);
            ps.setInt(2, p_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("storage") >= sum) {
                    f = true;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;

    }

    public int getStorage(String name, int p_id) {
        int sum = 0;
        int s_id = this.findByName(name).getId();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from shop_product where s_id =? and p_id=?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, s_id);
            ps.setInt(2, p_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getInt("storage");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public Shop getShop(String name){
        Shop shop = new Shop();
        int s_id = this.findByName(name).getId();
        ArrayList<Integer> productlist=new ArrayList();
        ArrayList<Integer> storagelist=new ArrayList();
        try {
            cn = connecter.getConnetcion();
            String sql = "select * from shop_product where s_id =? ;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, s_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productlist.add(rs.getInt("p_id"));
                storagelist.add(rs.getInt("storage"));
            }
            shop.setProductlist(productlist);
            shop.setStoragelist(storagelist);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shop;

    }
}
