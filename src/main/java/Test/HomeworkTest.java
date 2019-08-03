package Test;

import Bean.Homework;
import Bean.HomeworkDetail;
import Service.HomeworkService;

import java.util.ArrayList;
import java.util.Date;

public class HomeworkTest {
    public static void main(String[] args) {
        HomeworkService hs = new HomeworkService();
        Homework homework = new Homework("作业2","结对",new Date(2019-1900,6,22));
        ArrayList<String> a = new ArrayList<>();
        a.add("完成2个数组相加");
        a.add("数组为String类型");
//        hs.addHomework(homework);
//        hs.addDetail("1563756799998384755",a);
//        Homework h = hs.showHomework(new Date());
//        System.out.println(h.getId());
//        ArrayList<String> s =hs.showDetails(h);
//        for (int i=0;i<s.size();i++){
//            System.out.println(s.get(i));
//        }
//           ArrayList<HomeworkDetail> b =hs.showAll();
//        for (int i=0;i<b.size();i++){
//            System.out.println(b.get(i).getName());
//        }
    }
}
