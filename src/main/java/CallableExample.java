import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 创建Callable任务
        Callable<String> task = new Callable<String>() {
            public String call() throws Exception {
                // 模拟耗时操作
                Thread.sleep(1000);
                return "Hello, World!";
            }
        };


        // 提交任务并获取Future对象
        Future<String> future = executor.submit(task);

        // 等待任务执行完成并获取结果
        String result = future.get();

        System.out.println(result);

        // 关闭线程池
        executor.shutdown();
    }
}