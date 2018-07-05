package com.intellicentrics.visitor.kiosk.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.intellicentrics.visitor.kiosk.document.FacilityMaintenance;
public interface FacilityMaintenanceRepository extends  MongoRepository<FacilityMaintenance,String>, PagingAndSortingRepository<FacilityMaintenance, String> {
       
}
