package br.edu.unitri.posjava.tcc.med4you.repository;

import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.MedicalPrescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by edufratari on 01/08/18.
 */
public interface MedicalPrescriptionRepository extends JpaRepository<MedicalPrescription, Long> {

    @Query("SELECT r FROM MedicalPrescription r WHERE r.user.id = :userId")
    List<MedicalPrescription> findByUserId(@Param("userId") Long userId);

}
