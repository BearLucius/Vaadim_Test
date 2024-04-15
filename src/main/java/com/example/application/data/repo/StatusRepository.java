package com.example.application.data.repo;


import com.example.application.data.obj.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
