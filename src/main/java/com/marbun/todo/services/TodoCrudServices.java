package com.marbun.todo.services;

import com.marbun.todo.dto.requests.TodosRequests;
import com.marbun.todo.entities.TodoCategory;
import com.marbun.todo.entities.Todos;

import java.util.List;

public interface TodoCrudServices {
    List<Todos> findAllTodos();
    List<TodoCategory> findAllTodoCategories();
    Todos findById(Integer id);
    Todos save(TodosRequests requests);
    Todos update(Integer id,TodosRequests requests);
}
