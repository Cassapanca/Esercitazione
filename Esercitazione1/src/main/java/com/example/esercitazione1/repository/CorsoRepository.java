package com.example.esercitazione1.repository;

import com.example.esercitazione1.dominio.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
}