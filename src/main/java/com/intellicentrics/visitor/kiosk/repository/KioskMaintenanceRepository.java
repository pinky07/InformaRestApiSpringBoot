package com.intellicentrics.visitor.kiosk.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.intellicentrics.visitor.kiosk.document.KiosksMaintenance;
public interface KioskMaintenanceRepository extends MongoRepository<KiosksMaintenance,String>, PagingAndSortingRepository<KiosksMaintenance, String> {
	
}
