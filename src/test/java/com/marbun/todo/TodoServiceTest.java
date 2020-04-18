package com.marbun.todo;

import com.marbun.todo.entities.Todos;
import com.marbun.todo.repositories.TodoCategorierRepository;
import com.marbun.todo.repositories.TodoRepository;
import com.marbun.todo.services.TodoCrudServices;
import com.marbun.todo.services.TodoCrudServicesImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {
    private Logger logger = LoggerFactory.getLogger(TodoServiceTest.class);
    static Todos todos = new Todos(1, "todo1");
    private static final Optional<Todos> TODO_TEST = Optional.of(todos);
    private static final List<Todos> TODO_TEST_LIST = Arrays.asList(todos);

    TodoCategorierRepository todoCategorierRepository;
    TodoRepository todoRepository;
    TodoCrudServices todoCrudServices;

    @Before
    public void setup() {
        todoCategorierRepository= todoCategorierRepository();
        todoRepository = todoRepository();
        todoCrudServices = new TodoCrudServicesImpl(todoCategorierRepository, todoRepository);
    }

    private TodoRepository todoRepository() {
        TodoRepository mockTodoRepos = Mockito.mock(TodoRepository.class);
        Mockito.when(mockTodoRepos.findById(1)).thenReturn(TODO_TEST);
        Mockito.when(mockTodoRepos.findAll()).thenReturn(TODO_TEST_LIST);
        return  mockTodoRepos;
    }

    private TodoCategorierRepository todoCategorierRepository() {
        TodoCategorierRepository mock = Mockito.mock(TodoCategorierRepository.class);
        return  mock;
    }

    @Test
    public void test_getAll_Todos(){
        Assert.assertEquals(todoCrudServices.findAllTodos(),TODO_TEST_LIST);
    }

    @Test
    public void findByIdTodo(){
        Assert.assertEquals(todoCrudServices.findById(1),TODO_TEST.get());
    }

}
