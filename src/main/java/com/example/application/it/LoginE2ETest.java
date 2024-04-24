package com.example.application.it;

import com.example.application.views.list.LoginViewElement;
import com.vaadin.flow.component.login.testbench.LoginFormElement;
import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.BrowserTestBase;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.parallel.Browser;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


// @RunLocally(Browser.FIREFOX)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class LoginE2ETest extends BrowserTestBase {

    /*

   @Autowired используется для автоматического связывания компонентов бина между собой.
   Она позволяет автоматически настраивать свойства бина и методы, упрощая тем самым процесс инъекции зависимостей

     */

    @Autowired
    Environment env;

    static {
        // Prevent Vaadin Development mode to launch browser window
        System.setProperty("vaadin.launch-browser", "false");
    }

    /*

    Используется для обозначения того, что аннотированный метод должен выполняться перед каждым методом
    @Test, @RepeatedTest, @ParameterizedTest, или @TestFactory в текущем классе

     */

    @BeforeEach
    void openBrowser() {
        getDriver().get("http://localhost:" +
            env.getProperty("local.server.port") + "/"); 
    }

    /*

    @BrowserTest - тип тестирования, который проверяет, работает ли приложение так, как ожидается,
    в нескольких браузерах, операционных системах и устройствах

     */

    @BrowserTest
    public void loginAsValidUserSucceeds() {
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        assertTrue(loginView.login("user", "password"));
    }
    @BrowserTest
    public void loginAsInvalidUserFails() {
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        assertFalse(loginView.login("user", "invalid"));
    }

}