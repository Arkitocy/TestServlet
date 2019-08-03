package Test;

import Bean.Toy;
import Service.ToyService;

import java.util.ArrayList;

public class ToyTest {
    public static void main(String[] args) {
        ToyService ts =new ToyService();
        ArrayList<Toy> arrayList = ts.showAll();
        for (int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(i).getName());
        }
    }
}
