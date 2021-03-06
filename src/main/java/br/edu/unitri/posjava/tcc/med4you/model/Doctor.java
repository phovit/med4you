package br.edu.unitri.posjava.tcc.med4you.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Created by edufratari on 01/08/18.
 */
@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String phone;

	private String crm;

	private String email;

	private String clinicAddress;
	
	@OneToMany(mappedBy = "doctor")
	private List<MedicalPrescription> medicalPrescriptions = new ArrayList<>();

	public Doctor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

}
