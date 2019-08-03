package Test;

import Service.ParkService;

import java.util.Date;


public class TestCarPark {
    public static void main(String[] args) throws Exception {
        ParkService pc = new ParkService();
        pc.in("沪A2B137", new Date());
        pc.out("沪A2B137", new Date(2019 - 1900, 6, 13, 20, 8));
    }
}
