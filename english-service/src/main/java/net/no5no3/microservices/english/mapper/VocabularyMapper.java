package net.no5no3.microservices.english.mapper;

import net.no5no3.microservices.english.model.Vocabulary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VocabularyMapper {
    Vocabulary selectByLetter(String letter);
    int insert(Vocabulary vocabulary);
    int update(Vocabulary vocabulary);
    int delete(Integer id);
    List<Vocabulary> groupByClassify(@Param("classify") String classify);
    List<String> group();
}
