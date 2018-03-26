package net.no5no3.microservices.account.mapper;

import net.no5no3.microservices.account.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User selectByName(String name);
    User selectByOpenid(String openid);
    int insert(User user);
    int update(User user);
    int delete(Integer id);
}
