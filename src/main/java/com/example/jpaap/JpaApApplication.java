package com.example.jpaap;

import com.example.jpaap.entities.Patient;
import com.example.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
//50 min
@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
@Autowired
private PatientRepository patientRepository ;
    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i =0 ; i < 20 ; i++){
            patientRepository.save(new Patient(null , "fatima",new Date(),false,(int)(Math.random()*100))) ;
            patientRepository.save(new Patient(null , "hamza",new Date(),true,(int)(Math.random()*100))) ;
            patientRepository.save(new Patient(null , "manal",new Date(),false,(int)(Math.random()*100))) ;
            patientRepository.save(new Patient(null , "tester",new Date(),true,(int)(Math.random()*100))) ;
        }
        // list <-> page
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5)) ;
        System.out.println("Total pages :  " + patients.getTotalPages());
        System.out.println("Total elements  :  "+patients.getTotalElements());
        System.out.println("Num current page :  "+patients.getNumber());
        List<Patient> content = patients.getContent() ;
        Page<Patient> byMalade= patientRepository.findByMalade(true,  PageRequest.of(0,4));
        patients.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            if(p.isMalade())
                System.out.println("malade");
            else  System.out.println("not malade");
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
            System.out.println("------------------------------------");


        });
        System.out.println("---------------BY MALADE --------------------");
        byMalade.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
            if(p.isMalade())
                System.out.println("malade");
            System.out.println("-----------------------------------");


        });
        Patient patient=patientRepository.findById(1L).orElseThrow(()->new RuntimeException(
                "Patient not found"
        )) ;
        //  Patient patient1=patientRepository.findById(1L).get();
        System.out.println(patient.getId());
        System.out.println(patient.getNom());
        System.out.println(patient.getDateNaissance());

        patient.setScore(2);
        patientRepository.save(patient) ;
        patientRepository.deleteById(1L);
        System.out.println("------------------ chercher patient -----------------");

        List<Patient> patientCherche = patientRepository.chercherPatient(null , null , "hamza") ;
        patientCherche.forEach(p->
                {
                    System.out.println( "    score : " +p.getScore() +" nom :  "+ p.getNom() );
                });


    }
}
