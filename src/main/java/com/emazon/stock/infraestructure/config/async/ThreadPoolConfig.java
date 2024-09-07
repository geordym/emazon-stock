package com.emazon.stock.infraestructure.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    public static final int CORE_POOL_SIZE = 2;
    public static final int CORE_MAX_POOL_SIZE = 2;
    public static final int CORE_QUEUE_CAPACITY = 100;
    public static final String ASYNC_TASK_PREFIX = "SupplyingAsync-";

    @Bean("threadPoolExecutor")
    public TaskExecutor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(CORE_MAX_POOL_SIZE);
        executor.setQueueCapacity(CORE_QUEUE_CAPACITY);
        executor.setThreadNamePrefix(ASYNC_TASK_PREFIX);
        executor.initialize();
        return executor;
    }


}
