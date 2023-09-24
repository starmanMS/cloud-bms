package com.ms.service.client;

import com.ms.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("book-service")
public interface BookClient {
    //路径保证和其他微服务提供的一致即可
    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid); //参数和返回值也保持一致
}
