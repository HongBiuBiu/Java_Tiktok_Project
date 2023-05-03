package org.n3r.idworker;

import org.n3r.idworker.strategy.DefaultRandomCodeStrategy;

public class Code {
    private static RandomCodeStrategy strategy;

    static {
        RandomCodeStrategy strategy = new DefaultRandomCodeStrategy();
        strategy.init();
        configure(strategy);
    }

    public static synchronized void configure(RandomCodeStrategy custom) {
        if (strategy == custom) return;
        if (strategy != null) strategy.release();

        strategy = custom;
    }

    public static synchronized String next() {
        long workerId = Id.getWorkerId();
        int prefix = strategy.prefix();
        int next = strategy.next();

        return String.format("%d-%03d-%06d", workerId, prefix, next);
    }
}
