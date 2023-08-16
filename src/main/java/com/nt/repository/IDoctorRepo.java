package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Doctor;

public interface IDoctorRepo extends JpaRepository<Doctor, Integer> {
	
	@Modifying
	@Query("DELETE FROM Doctor WHERE income>=:start and income<=:end") // HQL query
	public int deleteDoctorsByIncomeRange(double start, double end);

	
	
}
