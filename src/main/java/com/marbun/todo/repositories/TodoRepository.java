package com.marbun.todo.repositories;

import com.marbun.todo.entities.Todos;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todos,Integer> {
}
