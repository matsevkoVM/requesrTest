import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorConfiguration {

    @PostConstruct
    public void threadPoolExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(500000);
        executor.setThreadNamePrefix("THR-");
        executor.initialize();
    }
}

