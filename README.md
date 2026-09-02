# COS 226 Practical 3: Test-and-Set Locks and Contention

## Overview
This explores spin locks based on the atomic Test-and-Set (TAS) operation and investigates their performance degradation under high thread contention.

## Core Goals
1. **Implement a basic Test-and-Set (TAS) Lock**: Use an atomic `testAndSet()` operation to build a simple spin lock that guarantees mutual exclusion.
2. **Optimize the Lock (TTAS)**: Reduce the unnecessary cache-coherence traffic caused by the naive TAS lock by checking the lock state with a normal read before attempting the expensive atomic operation.
3. **Contention Experiment**: Compare the execution time and atomic invocation counts of both implementations across different thread loads (2 to 32 threads) to prove the efficiency of the optimized lock.
