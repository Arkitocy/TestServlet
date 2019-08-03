package Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private String name;
    private Date ordertime;
    private BigDecimal sumprice;
    private ArrayList<Integer> arrayList;

    public Order() {

    }

    public Order(String name, ArrayList<Integer> arrayList) {
        this.name = name;
        this.ordertime = new Date();
        this.sumprice = null;
        this.arrayList = arrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public BigDecimal getSumprice() {
        return sumprice;
    }

    public void setSumprice(BigDecimal sumprice) {
        this.sumprice = sumprice;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void show() {
        System.out.println("订单id：" + this.getId());
        System.out.println("订单名：" + this.getName());
        System.out.println("订单时间：" + this.getOrdertime());
        System.out.println("订单总价：" + this.getSumprice());
        System.out.print("订单内容：");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print("商品id：" + arrayList.get(i) + " ");
        }
        System.out.println("");
    }
}
