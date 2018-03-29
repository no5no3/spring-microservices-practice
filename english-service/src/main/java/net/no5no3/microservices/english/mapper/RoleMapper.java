package net.no5no3.microservices.english.mapper;

import net.no5no3.microservices.english.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper {
    Role selectById(Integer id);
//    int insert(User user);
//    int update(User user);
//    int delete(Integer id);
}
