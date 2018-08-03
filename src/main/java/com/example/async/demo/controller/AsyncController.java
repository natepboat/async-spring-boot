package com.example.async.demo.controller;

import com.example.async.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("async")
public class AsyncController {
    private final AsyncService asyncService;

    @Autowired
    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("void")
    @Transactional
    public ResponseEntity<Void> asyncVoid() throws InterruptedException {
        asyncService.asyncVoidProcess();
        System.out.println("response void rest result");
        return ResponseEntity.ok().build();
    }

    @GetMapping("return")
    @Transactional
    public ResponseEntity<Void> asyncReturn() throws InterruptedException, ExecutionException {
        Future<List<String>> future = asyncService.asyncReturnProcess();
        System.out.println(String.join(", ", future.get()));
        System.out.println("response return rest result");
        return ResponseEntity.ok().build();
    }
}

