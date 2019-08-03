package Test;

import Bean.Shop;
import Service.ShopService;

public class ShopTest {
    public static void main(String[] args) {
        Shop shop1 = new Shop("shop1");
        ShopService ss = new ShopService();
        ss.addShop(shop1);
        ss.addProduct("shop1",1,30);//通过add方法，如果已有货物就会添加库存，没有则直接添加
        ss.addProduct("shop1",2,10);

        ss.getShop("shop1").show();
        ss.soldProduct("shop1",1,30);
        ss.getShop("shop1").show();

    }
}
