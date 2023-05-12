import java.util.*;

public class test07 {
    public static void main(String[] args) {
        //Collections解决
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i <30; i++) {
            new Thread(()->{
                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
