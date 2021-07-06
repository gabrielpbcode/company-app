package com.app.myapp.repository;

import com.app.myapp.model.Initializer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitializerRepository extends JpaRepository<Initializer, Long> {
    Initializer findByValue(String value);
}
