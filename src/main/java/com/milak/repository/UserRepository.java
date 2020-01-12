package com.milak.repository;

import com.milak.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Mapper
public interface UserRepository {

    User findByUsername(String username);

    User getUserByUuid(String uuid);

    void createUser(User user);

}
