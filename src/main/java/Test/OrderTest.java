package Test;

import Bean.Order;
import Service.OrderService;

import java.util.ArrayList;

public class OrderTest {
    public static void main(String[] args) {
        Order order1 = new Order("order57", new ArrayList<Integer>() {{
            add(1);
            add(4);
        }});

        Order order2 = new Order("order9", new ArrayList<Integer>() {{
            add(2);
            add(3);
        }});
        OrderService os = new OrderService();
        os.addOrder(order1);
//        os.getOrder(30).show();
//        os.deleteOrder();
//        os.getOrder(23).show();
//        os.updateOrder(30,order2);
//        os.getOrder(30).show();
    }

}
