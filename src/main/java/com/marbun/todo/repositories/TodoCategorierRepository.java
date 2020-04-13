package com.marbun.todo.repositories;

import com.marbun.todo.entities.TodoCategory;
import org.springframework.data.repository.CrudRepository;

public interface TodoCategorierRepository extends CrudRepository<TodoCategory,Integer> {
}
