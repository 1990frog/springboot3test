package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final RedissonClient redissonClient;

    @GetMapping
    public String test(String name) {
        RLock lock = redissonClient.getLock(name);
        lock.lock(30, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread());
        try {
            Thread.sleep(10000);
            return name + LocalDateTime.now();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
