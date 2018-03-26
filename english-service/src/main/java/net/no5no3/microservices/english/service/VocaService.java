package net.no5no3.microservices.english.service;

import net.no5no3.microservices.english.model.Vocabulary;

import java.util.List;

public interface VocaService {
    int add(List<Vocabulary> vocabulary);
}
