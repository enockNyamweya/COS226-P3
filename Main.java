public class Main 
{

    private static final int[] THREAD_COUNTS = {2, 4, 8, 16, 32};
    private static final int NUMBER_OF_RUNS = 5;
    private static final int INCREMENTS_PER_THREAD = 1000000;
    private static int counter = 0;

    static class TestThread extends Thread {
        private Lock lock;

        public TestThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for(int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                lock.lock();
                counter++;
                lock.unlock();
            }
        }
    }

    private static long runTests(Lock lock, int threadCount) throws InterruptedException {
        counter = 0;
        Thread[] threads = new Thread[threadCount];
        long startTime = System.nanoTime();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new TestThread(lock);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int threadCount : THREAD_COUNTS) {
            System.out.println("\nNum Threads -> " + threadCount);

            long tasTotalTime = 0;

            for (int run = 0; run < NUMBER_OF_RUNS; run++) {
                TASLock tas = new TASLock();
                long time = runTests(tas, threadCount);
                tasTotalTime += time;
                System.out.println("Run " + (run+1) + ": " + time + " ms");
            }

            System.out.println("Average: " + (tasTotalTime / NUMBER_OF_RUNS) + " ms");

            long ttasTotalTime = 0;

            for (int run = 0; run < NUMBER_OF_RUNS; run++) {
                TTASLock ttas = new TTASLock();
                long time = runTests(ttas, threadCount);
                ttasTotalTime += time;
                System.out.println("Run " + (run+1) + ": " + time + " ms");
            }

            System.out.println("Average: " + (ttasTotalTime / NUMBER_OF_RUNS) + " ms");
        }
    }
}