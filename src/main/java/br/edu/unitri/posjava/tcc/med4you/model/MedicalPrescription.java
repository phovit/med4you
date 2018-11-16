package br.edu.unitri.posjava.tcc.med4you.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by edufratari on 01/08/18.
 */
@Entity
public class MedicalPrescription {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String cid;

    private String medicalPrescriptionText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public MedicalPrescription() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMedicalPrescriptionText() {
        return medicalPrescriptionText;
    }

    public void setMedicalPrescriptionText(String medicalPrescriptionText) {
        this.medicalPrescriptionText = medicalPrescriptionText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
