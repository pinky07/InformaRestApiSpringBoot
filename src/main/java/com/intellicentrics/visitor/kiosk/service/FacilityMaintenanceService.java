package com.intellicentrics.visitor.kiosk.service;

import java.util.Optional;

import com.intellicentrics.visitor.kiosk.document.FacilityMaintenance;

public interface FacilityMaintenanceService {

	void deleteByfaclilityId(String id);
	
	 Optional<FacilityMaintenance> findByfacilityId(String id);
	 

}
