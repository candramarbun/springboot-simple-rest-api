package com.marbun.todo.controllers;

import com.marbun.todo.dto.requests.TodosRequests;
import com.marbun.todo.dto.responses.GeneralResponse;
import com.marbun.todo.services.TodoCrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodosController {
    private TodoCrudServices todoCrudServices;

    @Autowired
    public TodosController(TodoCrudServices todoCrudServices) {
        this.todoCrudServices = todoCrudServices;
    }

    @GetMapping("")
    public GeneralResponse showListTodos(){
        return GeneralResponse.response(todoCrudServices.findAllTodos());
    }

    @GetMapping("/categories")
    public GeneralResponse showAllCategories(){
        return GeneralResponse.response(todoCrudServices.findAllTodoCategories());
    }

    @PostMapping("/save")
    public GeneralResponse saveTodo(@RequestBody TodosRequests requests){
        return GeneralResponse.response(todoCrudServices.save(requests));
    }
    @GetMapping("/{id}/detail")
    public GeneralResponse getDetailTodoById(@PathVariable("id") Integer id){
        return GeneralResponse.response(todoCrudServices.findById(id));
    }

    @PostMapping("/{id}/update")
    public GeneralResponse updateTodo(@PathVariable("id") Integer id,@RequestBody TodosRequests requests){
        return GeneralResponse.response(todoCrudServices.update(id,requests));
    }
}
