package Bean;

import java.util.ArrayList;

public class Shop {
    private int id;
    private String name;
    private ArrayList<Integer> productlist;
    private ArrayList<Integer> storagelist;


    public Shop() {

    }

    public Shop(String name) {
        this.name = name;
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

    public ArrayList<Integer> getProductlist() {
        return productlist;
    }

    public void setProductlist(ArrayList<Integer> productlist) {
        this.productlist = productlist;
    }

    public ArrayList<Integer> getStoragelist() {
        return storagelist;
    }

    public void setStoragelist(ArrayList<Integer> storagelist) {
        this.storagelist = storagelist;
    }

    public void show() {
        for (int i = 0; i < productlist.size(); i++) {
            System.out.println("商品id：" + productlist.get(i) + " 库存：" + storagelist.get(i));
        }
        System.out.println();
    }
}
