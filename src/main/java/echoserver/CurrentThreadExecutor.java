package echoserver;

import java.util.concurrent.Executor;

class CurrentThreadExecutor implements Executor {
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
