package org.example;

import org.example.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)                                                        //
@SpringBootTest                                                                     //пойдет в наш package(для этого и указываем тестах package)
public class LoginTest   {

    @Autowired
    private MainController mainController;

    @Test                                                                           //min test
    public void test() throws Exception{
        assertThat(mainController).isNotNull();
    }
}
