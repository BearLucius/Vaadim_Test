package com.example.application.data.repo;


import com.example.application.data.obj.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
