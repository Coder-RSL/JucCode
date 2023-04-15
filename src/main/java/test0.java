public class test0 {
    public static void main(String[] args) {
        Thread a =new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + " :: " + Thread.currentThread().isDaemon());
            while (true) {
            }
        },"aa");
        a.setDaemon(true);

        a.start();




        System.out.println(Thread.currentThread().getName()+" is over");
    }
}
