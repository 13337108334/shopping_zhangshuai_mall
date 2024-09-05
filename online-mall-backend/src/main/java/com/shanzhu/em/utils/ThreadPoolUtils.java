package com.shanzhu.em.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 多线程并行工具类
 * @author zhangshuai
 * @Date 2024/08/24
 */
public final class ThreadPoolUtils {

    /**
     * 通用线程池，适用于 IO 密集型逻辑提高性能，如批量调用 RPC 服务，该线程池应用内
     * 共享，有线程池满的风险，使用前需自行评估风险和线程池配置是否适用你的场景。
     */
    private static final ThreadPoolExecutor COMMON_THREAD_POOL;

    static {
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 5;
        int maximumPoolSize = corePoolSize;
        int keepAliveTime = 0;
        TimeUnit minutes = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(200);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("COMMON-THREAD-POOL-THREAD-%d").build();
        COMMON_THREAD_POOL = new ThreadPoolExecutor(corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            minutes,
            workQueue,
            threadFactory);
    }

    public static ThreadPoolExecutor getCommonThreadPool() {
        return COMMON_THREAD_POOL;
    }


    /*
     * 用法：
     *                                  // 多线程并行执行
     *                                 List<Runnable> runnableList = new ArrayList<>();
     *                                 runnableList.add(() -> {
     *                                     // 业务逻辑
     *                                 });
     * ThreadPoolUtils.executeAll(runnableList, 10, TimeUnit.SECONDS);
     *
     */
    public static void executeAll(List<Runnable> tasks, long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, RejectedExecutionException, CancellationException {
        if (CollectionUtils.isEmpty(tasks)) {
            return;
        }

        // map to runnable
        List<Callable<Void>> collect = tasks.parallelStream()
            .map(runnable -> {
                return new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        runnable.run();
                        return null;
                    }
                };
            })
            .collect(Collectors.toList());

        List<Future<Void>> futures = COMMON_THREAD_POOL.invokeAll(collect, timeout, unit);
        for (Future<Void> future : futures) {
            future.get();
        }
    }


    public static <T> List<T> executeAllWithResult(List<Callable<T>> tasks, long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, RejectedExecutionException, CancellationException {
        if (CollectionUtils.isEmpty(tasks)) {
            return Collections.emptyList();
        }
        List<Future<T>> futures = COMMON_THREAD_POOL.invokeAll(tasks, timeout, unit);
        List<T> result = new ArrayList<>();
        for (Future<T> future : futures) {
            result.add(future.get());
        }
        return result;
    }

    public static void submit(Runnable task) {
        COMMON_THREAD_POOL.execute(task);
    }

    public static <T> List<CallableResultModel<T>> executeAllWithCallableResult(List<Callable<CallableResultModel<T>>> tasks, long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, RejectedExecutionException, CancellationException {
        if (CollectionUtils.isEmpty(tasks)) {
            return Collections.emptyList();
        }
        List<Future<CallableResultModel<T>>> futures = COMMON_THREAD_POOL.invokeAll(tasks, timeout, unit);
        List<CallableResultModel<T>> targetList = new ArrayList<>();
        for (Future<CallableResultModel<T>> future : futures) {
            CallableResultModel<T> resultModel = future.get();
            if (resultModel == null) {
                continue;
            }
            targetList.add(resultModel);
        }
        return targetList;
    }

    public static class CallableResultModel<T> implements Serializable {

        private static final long serialVersionUID = 2773870297304555047L;
        private String key;
        private T value;

        public CallableResultModel() {
            super();
        }

        public CallableResultModel(String key, T value) {
            super();
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

}
