import java.util.concurrent.atomic.AtomicBoolean;

public class TASLock implements Lock
{

    private final AtomicBoolean locked = new AtomicBoolean(false);

    /* Do not modify this method */
    private boolean testAndSet() 
    {
        return locked.getAndSet(true);
    }

    public void lock() 
    {
        // Spin continuously until the atomic testAndSet() returns false,
        // which indicates that we successfully acquired the lock.
        while (testAndSet()) {}
    }

    public void unlock() 
    {
        // Release the lock by resetting the state to false,
        // allowing other spinning threads to acquire it.
        locked.set(false);
    }
    
}