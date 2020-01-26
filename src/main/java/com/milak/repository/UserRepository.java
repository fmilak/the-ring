package com.milak.repository;

import com.milak.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {

    User findByUsername(String username);

    User getUserByUuid(String uuid);

    void createUser(User user);

    void updateCurrentLimit(String uuid);

}
