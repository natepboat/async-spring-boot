package com.example.async.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class AsyncService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AsyncService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Async
    public void asyncVoidProcess() throws InterruptedException {
        jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", "async1");
        System.out.println("Start async void process Thread" + Thread.currentThread().getName());
        Thread.sleep(2000);
        jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", "async2");
        System.out.println("End async void process Thread" + Thread.currentThread().getName());
    }

    @Async
    public Future<List<String>> asyncReturnProcess() throws InterruptedException {
        System.out.println("Start async return process Thread" + Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("End async return process Thread" + Thread.currentThread().getName());

        return new AsyncResult<>(jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME")));
    }
}
