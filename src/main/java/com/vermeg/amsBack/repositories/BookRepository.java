package com.vermeg.amsBack.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.amsBack.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
