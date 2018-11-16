package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by edufratari on 01/08/18.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    @Query("SELECT m FROM Doctor m WHERE lower(m.name) like CONCAT('%',lower(:name),'%')")
    List<Doctor> findByName(@Param("name") String name);
}
