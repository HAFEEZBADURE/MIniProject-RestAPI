package com.nt.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DOCTOR_INFO")
public class Doctor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer docId;

	@Column(length = 20, name = "DOC_NAME")
	private String docName;

	@Column(length = 20, name = "SPECIALIZATION")
	private String specialization;

	@Column(name = "INCOME")
	private Double income;

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Doctor(Integer docId, String docName, String specialization, Double income) {
		super();
		this.docId = docId;
		this.docName = docName;
		this.specialization = specialization;
		this.income = income;
	}

	
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Doctor [docId=" + docId + ", docName=" + docName + ", specialization=" + specialization + ", income="
				+ income + "]";
	}
	
	

}
