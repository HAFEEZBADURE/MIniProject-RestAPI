package com.nt.service;

import java.util.Iterator;
import java.util.List;

import com.nt.Exception.DoctorNotFoundException;
import com.nt.entity.Doctor;

public interface IDoctorMgmt {

	public String registerDoctor(Doctor doctor);

	public List<Doctor> GetAllDoctors();

	public Doctor getDoctorById(int id);

	public Doctor getDoctorByid(int id) throws DoctorNotFoundException;

	public String UpdateDoctor(Doctor doctor) throws DoctorNotFoundException;

	public String deleteDoctorById(int docId) throws DoctorNotFoundException;

	public String deleteDoctorByIncomeRange(double start, double end);
	
	public String HikeDoctorIncomeByPercentage(int docId, double percentage) throws DoctorNotFoundException;
}
