package com.example.jpaap.repositories;

import com.example.jpaap.entities.Patient;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.* ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.*;

public interface PatientRepository extends JpaRepository<Patient,Long> {
   // public List<Patient> findByMalade(boolean m) ;
public Page<Patient> findByMalade(boolean m , Pageable pageable) ;
List<Patient> findByMaladeAndAndScoreLessThan(boolean m , int s) ;
   List<Patient> findByMaladeIsTrueAndScoreLessThan( int s) ;
   List<Patient> findByDateNaissanceBetween(Date d1 , Date d2) ;
@Query("select p from Patient  p where p.dateNaissance between  :x and :y or p.nom like :z")
   List<Patient> chercherPatient(@Param("x") Date d1 , @Param("y") Date d2 ,@Param("z") String nom ) ;

}
