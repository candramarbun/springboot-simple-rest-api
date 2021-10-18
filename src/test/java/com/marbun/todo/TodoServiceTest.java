package com.marbun.todo;

import com.marbun.todo.controllers.TodosController;
import com.marbun.todo.entities.Todos;
import com.marbun.todo.repositories.TodoCategorierRepository;
import com.marbun.todo.repositories.TodoRepository;
import com.marbun.todo.services.TodoCrudServices;
import com.marbun.todo.services.TodoCrudServicesImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TodoServiceTest {
    Todos todoTest;
    List<Todos> todoTestList;
    @Mock
    TodoCategorierRepository todoCategorierRepository;
    @Mock
    TodoRepository todoRepository;
    @Mock
    TodoCrudServices todoCrudServices;
    @Mock
    TodosController todosController;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        todoCategorierRepository= todoCategorierRepository();
        todoRepository = todoRepository();
        todoCrudServices = todoCrudServices(todoCategorierRepository, todoRepository);
        todosController = todosController(todoCrudServices);
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        //set data
        Todos todos = new Todos(1, "todo1");
        todoTest = todos;
        todoTestList = Collections.singletonList(todos);
    }

    private TodoRepository todoRepository() {
        TodoRepository mockTodoRepos = Mockito.mock(TodoRepository.class);
        Mockito.when(mockTodoRepos.findById(1)).thenReturn(Optional.ofNullable(todoTest));
        Mockito.when(mockTodoRepos.findAll()).thenReturn(todoTestList);
        return  mockTodoRepos;
    }

    private TodoCategorierRepository todoCategorierRepository() {
        return Mockito.mock(TodoCategorierRepository.class);
    }

    private TodosController todosController(TodoCrudServices todoCrudServices){
        return new TodosController(todoCrudServices);
    }

    private TodoCrudServices todoCrudServices(TodoCategorierRepository todoCategorierRepository,TodoRepository todoRepository){
        TodoCrudServices todoCrudServicesMock = new TodoCrudServicesImpl(todoCategorierRepository,todoRepository);
        Mockito.when(todoCrudServicesMock.findAllTodos()).thenReturn(todoTestList);
        return todoCrudServicesMock;
    }

    @Test
    public void testGetAllTodos(){
        assertEquals(todoCrudServices.findAllTodos(),todoTestList);
    }

    @Test
    public void findByIdTodo(){
        assertEquals(todoCrudServices.findById(1),todoTest);
    }

    // integration test
    @Test
    public void integrationTestGetAllTodos(){
        try {
            this.mvc.perform(get("/todos").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
