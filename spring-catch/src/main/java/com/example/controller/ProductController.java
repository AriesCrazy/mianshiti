package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.model.Product;
import com.example.model.ProductQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

@RestController
@Service
public class ProductController {
    @GetMapping
    @Cacheable("product.select#86400")
    public List<Product> select(ProductQuery query) {

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id(1).name("华为meta60").build());
        products.add(Product.builder().id(2).name("华为meta80").build());
        if(ObjectUtil.isNotEmpty(query.getId())){
            products =  products.stream().filter(it->it.getId().equals(query.getId())).collect(Collectors.toList());
        }
        if(ObjectUtil.isNotEmpty(query.getName())){
            products =  products.stream().filter(it->it.getName().equals(query.getName())).collect(Collectors.toList());
        }
        return products;

    }

    @PostMapping(value = "/user", produces = "text/plain;charset=UTF-8")
    public String addUser(@RequestParam("name")  LocalDateTime name, @RequestParam("age") Integer age) {
        // 处理数据
        return "User added: " + name + ", " + age;
    }

    @GetMapping("{id}")
    @Cacheable(value = "product.select.byId")
    public Product selectById(@PathVariable Integer id) {
        return Product.builder().id(id).name("华为meta60").build();
    }
}
