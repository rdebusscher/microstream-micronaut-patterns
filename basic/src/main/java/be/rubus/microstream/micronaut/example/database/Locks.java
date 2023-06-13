package be.rubus.microstream.micronaut.example.database;

import jakarta.inject.Singleton;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

@Singleton
public class Locks {

    public static final ReentrantReadWriteLock USERS = new ReentrantReadWriteLock();

    public <T> T readAction(ReentrantReadWriteLock lock, Supplier<T> supplier) {
        lock.readLock().lock();
        try {
            return supplier.get();
        } finally {
            lock.readLock().unlock();
        }
    }

    public <T> T writeAction(ReentrantReadWriteLock lock, Supplier<T> supplier) {
        lock.writeLock().lock();
        try {
            return supplier.get();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
