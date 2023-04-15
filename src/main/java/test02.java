class Ticket{
    private int number = 3000000;
    public  void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName() + ": 卖出"+(number--)+" 剩下:"+ number);

        }
        else {
            System.out.println(Thread.currentThread().getName()+"已买光");
        }
    }
}

public class test02 {
    public static void main(String[] args) {
        Ticket ticket =new Ticket();
        Thread a=new Thread(new Runnable(){
            @Override
            public synchronized void run() {
                for (int i=0;i<3000000;i++){
                    ticket.sale();

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread b = new Thread(new Runnable(){
            int num=3;

            @Override
            public synchronized void run() {
                for (int i=0;i<10000;i++){
                    ticket.sale();
                    System.out.println("*****************************************************************************");
                    num++;
                }

            }
        },"t2");
        a.start();
        b.start();
    }
}
