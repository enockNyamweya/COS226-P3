
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class TTASLock implements Lock {
    private final AtomicBoolean state = new AtomicBoolean(false);
    private final AtomicLong testAndSetCount = new AtomicLong(0);

    private boolean testAndSet() {
        return state.getAndSet(true);
    }

    public void lock() {
        while (true) { 
            while(state.get()) {/*Wait until the lock has be released to attempt acquiring it */ }

            testAndSetCount.incrementAndGet();

            if(!testAndSet()) break;
        }
    }

    public void unlock() {
        state.set(false);
    }

    public long getTestAndSetCount() {
        return testAndSetCount.get();
    }
}
