package com.marbun.todo;

import com.marbun.todo.controllers.TodosController;
import com.marbun.todo.entities.Todos;
import com.marbun.todo.repositories.TodoCategorierRepository;
import com.marbun.todo.repositories.TodoRepository;
import com.marbun.todo.services.TodoCrudServices;
import com.marbun.todo.services.TodoCrudServicesImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TodoServiceTest {
    private Logger logger = LoggerFactory.getLogger(TodoServiceTest.class);
    static Todos todos = new Todos(1, "todo1");
    private static final Optional<Todos> TODO_TEST = Optional.of(todos);
    private static final List<Todos> TODO_TEST_LIST = Arrays.asList(todos);

    TodoCategorierRepository todoCategorierRepository;
    TodoRepository todoRepository;
    TodoCrudServices todoCrudServices;
    TodosController todosController;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setup() {
        todoCategorierRepository= todoCategorierRepository();
        todoRepository = todoRepository();
        todoCrudServices = todoCrudServices(todoCategorierRepository, todoRepository);
        todosController = todosController(todoCrudServices);
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    private TodoRepository todoRepository() {
        TodoRepository mockTodoRepos = Mockito.mock(TodoRepository.class);
        Mockito.when(mockTodoRepos.findById(1)).thenReturn(TODO_TEST);
        Mockito.when(mockTodoRepos.findAll()).thenReturn(TODO_TEST_LIST);
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
        Mockito.when(todoCrudServicesMock.findAllTodos()).thenReturn(TODO_TEST_LIST);
        return todoCrudServicesMock;
    }

    @Test
    public void testGetAllTodos(){
        Assert.assertEquals(todoCrudServices.findAllTodos(),TODO_TEST_LIST);
    }

    @Test
    public void findByIdTodo(){
        Assert.assertEquals(todoCrudServices.findById(1),TODO_TEST.get());
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
