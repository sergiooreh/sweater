package org.example.repos;

import org.example.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message>findByTag(String tag);                                                     //найти тег и вывести в листе(метод назван по правилам JPA Repositories Query creation...ссылка под видео)
}
