package com.example.application.services;

import com.example.application.data.obj.Company;
import com.example.application.data.obj.Contact;
import com.example.application.data.obj.Status;
import com.example.application.data.repo.CompanyRepository;
import com.example.application.data.repo.ContactRepository;
import com.example.application.data.repo.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    //Если Фильтр = Нуль или Фильтр = Пуст, тогда возвращаем ВСЕХ, иначе через ContactRepository.Search ищем через фильтр
    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }
    // Возвращает кол-во контактов
    public long countContacts() {
        return contactRepository.count();
    }
    // Удаление контакта
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }
    // Сохранение Контакта
    // Если Контакт = Нуль, выдать ошибку
    public void saveContact(Contact contact) {
        if (contact == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save(contact);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}