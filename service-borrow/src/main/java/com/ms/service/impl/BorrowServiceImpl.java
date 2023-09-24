package com.ms.service.impl;

import com.ms.entity.Book;
import com.ms.entity.Borrow;
import com.ms.entity.User;
import com.ms.entity.UserBorrowDetail;
import com.ms.mapper.BorrowMapper;
import com.ms.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper mapper;
    @Resource
    private RestTemplate restTemplate;
    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);
        //这里通过调用getForObject来请求其他服务，并将结果自动进行封装
        //获取User信息
        User user = restTemplate.getForObject("http://user-service/user/"+uid, User.class);
        //获取每一本书的详细信息
        List<Book> bookList = borrow
                .stream()
                .map(b -> restTemplate.getForObject("http://book-service/book/"+b.getBid(), Book.class))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }
}