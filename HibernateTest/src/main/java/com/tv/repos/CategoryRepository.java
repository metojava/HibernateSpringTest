package com.tv.repos;

import org.springframework.data.repository.CrudRepository;

import com.tv.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
