package com.example.application.data.obj;

import com.example.application.data.AbstractEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Formula;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Company extends AbstractEntity {
    /*
    Здесь мы делаем различные переменные, которые будут использоваться для передачи информации в базу данных
    Это у нас было при создании Клиент + Сервер. Как пример схожести, посмотрим на строку --
    Так же соединяются через связи Один к одному/Один ко многим/Многие ко Многим(Редко, и то, надо будет делать отдельную таблицу,
    куда передаются ключи)
     */
    @NotBlank
    private String name;
    @Formula("(select count(c.id) from Contact c where c.company_id = id)")
    private int employeeCount;

    public int getEmployeeCount(){
        return employeeCount;
    }

    // Здесь мы делаем связь один ко многим и делает его допускающим значение Null
    @OneToMany(mappedBy = "company")
    @Nullable
    private List<Contact> employees = new LinkedList<>();
    // Дальше идут переменные которые будут возвращать нам Имя/Покупателей
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Contact> employees) {
        this.employees = employees;
    }
}
