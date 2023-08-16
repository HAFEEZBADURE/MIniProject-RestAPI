package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Doctor;
import com.nt.service.IDoctorMngmtImpl;

@RestController
@RequestMapping("/doctor")
public class DoctorOperationsController {

	@Autowired
	private IDoctorMngmtImpl doctorservice;

	@PostMapping("Save")
	public ResponseEntity<String> registerDoctor(@RequestBody Doctor doctor) {

		try {
			// use service
			String resultMsg = doctorservice.registerDoctor(doctor);
			return new ResponseEntity<String>(resultMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("The problem is doctor registration", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/report")
	public ResponseEntity<?> showAllDoctors() {
		try {
			List<Doctor> list = doctorservice.GetAllDoctors();
			return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("No records found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Find/{id}")
	public ResponseEntity<?> fetchDoctorById(@PathVariable int id) {
		try {
			// use service

			Doctor doctor = doctorservice.getDoctorById(id);
			return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<String>("problem is getting record", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/FindByid/{id}")
	public ResponseEntity<?> FetchDoctorByid(@PathVariable int id) {
		try {
			Doctor doc = doctorservice.getDoctorByid(id);
			return new ResponseEntity<Doctor>(doc, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/modify")
	public ResponseEntity<?> ModifyDoctorInfo(@RequestBody Doctor doctor) {
		try {

			String resultMsg = doctorservice.UpdateDoctor(doctor);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoctorByid(@PathVariable Integer id) {
		try {

			String resultMsg = doctorservice.deleteDoctorById(id);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{start}/{end}")
	public ResponseEntity<?> DeleteDoctorByRange(@PathVariable double start, @PathVariable double end) {
		try {
			// user service
			String msg = doctorservice.deleteDoctorByIncomeRange(start, end);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/update/{id}/{percentage}")
	public ResponseEntity<String> UpdateIncomeOfDoctor(@PathVariable int id, @PathVariable double percentage) {
		try {
			String msg = doctorservice.HikeDoctorIncomeByPercentage(id, percentage);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
