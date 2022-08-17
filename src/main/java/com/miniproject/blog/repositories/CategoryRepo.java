package com.miniproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
