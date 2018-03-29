package net.no5no3.microservices.english.mapper;

import net.no5no3.microservices.english.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRoleMapper {
    List<UserRole> selectByUserAndRole(@Param("user") String user, @Param("role") String role);
    int insert(UserRole userRole);
    int update(UserRole userRole);
    int delete(Integer id);
}
