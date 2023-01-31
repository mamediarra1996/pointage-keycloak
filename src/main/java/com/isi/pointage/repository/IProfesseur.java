package com.isi.pointage.repository;

import com.isi.pointage.entity.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesseur extends JpaRepository<Professeur, Integer> {

}
