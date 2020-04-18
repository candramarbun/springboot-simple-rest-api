package com.marbun.todo.entities;


import javax.persistence.*;

@Entity
public class Todos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    private TodoCategory todoCategory;

    public Todos() {
    }

    public Todos(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoCategory getTodoCategory() {
        return todoCategory;
    }

    public void setTodoCategory(TodoCategory todoCategory) {
        this.todoCategory = todoCategory;
    }
}
