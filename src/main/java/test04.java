import java.util.concurrent.locks.ReentrantReadWriteLock;

public class test04 {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final test04 test = new test04();
        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();
        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

    }

    public void get(Thread thread) {
        rwl.readLock().lock();//加锁
        try{
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        }finally {
            rwl.readLock().unlock();
        }

    }

}
