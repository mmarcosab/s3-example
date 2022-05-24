package com.s3example.demo.adapters.controller;


import com.amazonaws.services.s3.model.Bucket;
import com.s3example.demo.adapters.representation.BucketObjectRepresentaion;
import com.s3example.demo.adapters.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/buckets/")
@RequiredArgsConstructor
public class ControllerTests {

    private final S3Service s3Service;

    @PostMapping(value = "/{bucketName}")
    public void createBucket(@PathVariable String bucketName){
        s3Service.createS3Bucket(bucketName);
    }

    @GetMapping
    public List<String> listBuckets(){
        var buckets = s3Service.listBuckets();
        var names = buckets.stream().map(Bucket::getName).collect(Collectors.toList());
        return names;
    }

    @DeleteMapping(value = "/{bucketName}")
    public void deleteBucket(@PathVariable String bucketName){
        s3Service.deleteBucket(bucketName);
    }

    @PostMapping(value = "/{bucketName}/objects")
    public void createObject(@PathVariable String bucketName, @RequestBody BucketObjectRepresentaion representaion) throws IOException {
        s3Service.putObject(bucketName, representaion);
    }

}
