import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 线程池练习：核心线程 -> 队列 -> 非核心线程 -> 拒绝
// javac ThreadPoolDemo.java && java ThreadPoolDemo
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 4,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                r -> new Thread(r, "demo-thread"),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            pool.execute(() -> {
                System.out.printf("[%s] 执行任务 %d%n", Thread.currentThread().getName(), taskId);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("完成任务数：" + pool.getCompletedTaskCount());
    }
}
