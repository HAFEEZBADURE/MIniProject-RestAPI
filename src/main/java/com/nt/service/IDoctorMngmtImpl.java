package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nt.Exception.DoctorNotFoundException;
import com.nt.entity.Doctor;
import com.nt.repository.IDoctorRepo;

@Service("doctorService")
public class IDoctorMngmtImpl implements IDoctorMgmt {

	@Autowired
	private IDoctorRepo doctorRepo;

	@Override
	public String registerDoctor(Doctor doctor) {

		int idVal = doctorRepo.save(doctor).getDocId();

		return "Doctor is saved with the id value " + idVal;
	}

	@Override
	public List<Doctor> GetAllDoctors() {

		List<Doctor> list = doctorRepo.findAll();
		list.sort((d1, d2) -> d1.getDocName().compareTo(d2.getDocName()));
		return list;
	}

	@Override
	public Doctor getDoctorById(int id) {
		Doctor doc = doctorRepo.findById(id).get();
		return doc;
	}

	@Override
	public Doctor getDoctorByid(int id) throws DoctorNotFoundException {
		// TODO Auto-generated method stub

		Doctor doct = doctorRepo.findById(id).orElseThrow(() -> new DoctorNotFoundException(id + " is not found"));
		return doct;
	}

	@Override
	public String UpdateDoctor(Doctor doctor) throws DoctorNotFoundException {

		Optional<Doctor> opt = doctorRepo.findById(doctor.getDocId());
		if (opt.isPresent()) {
			doctorRepo.save(doctor);
			return doctor.getDocId() + " Doctors details are updated";
		} else {
			throw new DoctorNotFoundException(doctor.getDocId() + " doctor is not found");
		}

	}

	@Override
	public String deleteDoctorById(int docId) throws DoctorNotFoundException {
		// get find the object
		Optional<Doctor> opt = doctorRepo.findById(docId);
		if (opt.isPresent()) {
			doctorRepo.deleteById(docId);
			return docId + " Id doctor is deleted";
		} else {
			throw new DoctorNotFoundException(docId + " doctor is not found");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteDoctorByIncomeRange(double start, double end) {
		// use service
		int count = doctorRepo.deleteDoctorsByIncomeRange(start, end);

		return count + " No. of doctors are deleted";
	}

	@Override
	public String HikeDoctorIncomeByPercentage(int docId, double percentage) throws DoctorNotFoundException {
		// load the object

		Optional<Doctor> opt = doctorRepo.findById(docId);
		if (opt.isPresent()) {
			Doctor doctor = opt.get();
			doctor.setIncome(doctor.getIncome() + (doctor.getIncome() * percentage / 100.0));
			doctorRepo.save(doctor);
			return "Doctor income is Hiked " + doctor.getIncome();

		} else {
			throw new DoctorNotFoundException(docId + " doctor is not found");
		}

	}

}
