package Test;

import Bean.Product;
import Service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class ProductTest {
    public static void main(String[] args) {
        Product product1 = new Product("产品5", "电子产品", new Date(), new Date(2019 - 1900, 11, 14, 15, 00, 0),new BigDecimal(2500));
        Product product2 = new Product("产品6", "家电", new Date(), new Date(2020 - 1900, 1, 13, 15, 00, 0),new BigDecimal(9999));
        ProductService ps = new ProductService();
//        ps.addProduct(product1);
//        ps.addProduct(product2);
//        ps.deleteProduct("产品1","日用");
//        ps.updateProduct("产品1","日用",product2);
//        Product p = ps.showProduct("产品2", "万用");
//        p.setName("2");
//        ps.updateProduct(p);
//        p.show();
       ArrayList<Product> ap = ps.showAll();
       for (int i=0;i<ap.size();i++){
           System.out.println(ap.get(i).getId());
       }


    }
}
