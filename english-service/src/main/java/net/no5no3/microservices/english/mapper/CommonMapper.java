package net.no5no3.microservices.english.mapper;

import net.no5no3.microservices.english.model.Common;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommonMapper {
    int insert(Common common);
}
