
import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock {
    private final AtomicBoolean state = new AtomicBoolean(false);

    public void lock() {
        while (true) { 
            while(state.get()) {/*Wait until the lock has be released to attempt acquaring it */ }
            if(state.getAndSet(true)) return;
        }
    }

    public void unlock() {
        state.set(false);
    }
}
