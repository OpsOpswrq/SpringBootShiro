package com.feng.mapper;

import com.feng.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User queryUserByName(String name);
}
