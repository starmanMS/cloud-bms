package com.ms.service;

import com.ms.entity.UserBorrowDetail;

public interface BorrowService {

    UserBorrowDetail getUserBorrowDetailByUid(int uid);
}