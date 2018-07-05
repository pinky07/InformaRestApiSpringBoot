package com.intellicentrics.visitor.kiosk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellicentrics.visitor.kiosk.document.FacilityMaintenance;
import com.intellicentrics.visitor.kiosk.repository.FacilityMaintenanceRepository;

import org.springframework.data.domain.Sort;
@Service
@Transactional
public class FacilityMaintenanceServiceImpl  implements FacilityMaintenanceService{

	private static final int PAGE_SIZE = 5;
	@Autowired
	private FacilityMaintenanceRepository facilityMaintenanceRepository;

	

	@Override
	public void deleteByfaclilityId(String id) {
		facilityMaintenanceRepository.deleteById(id);
		
	}

	@Override
	public Optional<FacilityMaintenance> findByfacilityId(String id) {
		
		return facilityMaintenanceRepository.findById(id);
	}

	

	
}
