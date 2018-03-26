package net.no5no3.microservices.english.service;

import net.no5no3.microservices.english.mapper.VocabularyMapper;
import net.no5no3.microservices.english.model.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VocaServiceImpl implements VocaService {

    @Autowired
    private VocabularyMapper vocabularyMapper;
    @Override
    @Transactional(readOnly = false)
    public int add(List<Vocabulary> vocabulary) {
        int r = 0;
        for(Vocabulary v : vocabulary){
            r += vocabularyMapper.insert(v);
        }
        return r;
    }
}
