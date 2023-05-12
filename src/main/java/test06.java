import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class printDemo{
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    private int flag = 1 ;

    public void printA(int k) throws InterruptedException {
        lock.lock();
        while(flag!=1){
            c1.await();
        }
        for(int i=0;i<5;i++){
            System.out.println("AA::"+i+"::"+k);
        }
        flag = 2;
        c2.signal();
        lock.unlock();
    }
    public void printB(int k) throws InterruptedException {
        lock.lock();
        while(flag!=2){
            c2.await();
        }
        for(int i=0;i<4;i++){
            System.out.println("BB::"+i+"::"+k);
        }
        flag = 3;
        c3.signal();
        lock.unlock();
    }
    public void printC(int k) throws InterruptedException {
        lock.lock();
        while(flag!=3){
            c3.await();
        }
        for(int i=0;i<3;i++){
            System.out.println("CC::"+i+"::"+k);
        }
        flag = 1;
        c1.signal();
        lock.unlock();
    }

}
public class test06 {

    public static void main(String[] args) {
        printDemo shareResource =new printDemo();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareResource.printA(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareResource.printB(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareResource.printC(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

    }
}
