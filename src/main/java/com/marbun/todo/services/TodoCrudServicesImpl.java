package com.marbun.todo.services;

import com.marbun.todo.dto.requests.TodosRequests;
import com.marbun.todo.entities.TodoCategory;
import com.marbun.todo.entities.Todos;
import com.marbun.todo.exception.GeneralException;
import com.marbun.todo.repositories.TodoCategorierRepository;
import com.marbun.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoCrudServicesImpl implements TodoCrudServices {
    private TodoCategorierRepository todoCategorierRepository;
    private TodoRepository todoRepository;

    @Autowired
    public TodoCrudServicesImpl(TodoCategorierRepository todoCategorierRepository, TodoRepository todoRepository) {
        this.todoCategorierRepository = todoCategorierRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todos> findAllTodos() {
        return (List<Todos>) todoRepository.findAll();
    }

    @Override
    public List<TodoCategory> findAllTodoCategories() {
        return (List<TodoCategory>) todoCategorierRepository.findAll();
    }

    @Override
    public Todos findById(Integer id) {
        return todoRepository.findById(id).orElseThrow(() -> new GeneralException(404,"Data Not Found!"));
    }

    @Override
    public Todos save(TodosRequests requests) {
        TodoCategory todoCategory = todoCategorierRepository.findById(requests.getCategoryId()).orElseThrow(() -> new GeneralException(404,"Data Not Found!"));

        Todos todos = new Todos();
        todos.setName(requests.getName());
        todos.setTodoCategory(todoCategory);
        todos.setDescription(requests.getDescription());
        return todoRepository.save(todos);
    }

    @Override
    public Todos update(Integer id,TodosRequests requests) {
        TodoCategory todoCategory = todoCategorierRepository.findById(requests.getCategoryId()).orElseThrow(() -> new GeneralException(404,"Data Not Found!"));

        Todos todos = todoRepository.findById(id).orElseThrow(() -> new GeneralException(404,"TODO Not Found!"));
        todos.setName(requests.getName());
        todos.setTodoCategory(todoCategory);
        todos.setDescription(requests.getDescription());
        return todoRepository.save(todos);

    }
}
