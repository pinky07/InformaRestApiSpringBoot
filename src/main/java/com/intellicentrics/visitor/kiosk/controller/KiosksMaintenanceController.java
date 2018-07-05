package com.intellicentrics.visitor.kiosk.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intellicentrics.visitor.kiosk.document.FacilityMaintenance;
import com.intellicentrics.visitor.kiosk.document.KiosksMaintenance;
import com.intellicentrics.visitor.kiosk.repository.KioskMaintenanceRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/kiosks")
public class KiosksMaintenanceController {
	@Autowired
	private KioskMaintenanceRepository kiosksMaintenanceRepository;

	private final Logger logger = LoggerFactory.getLogger(KiosksMaintenanceController.class);

	@ApiOperation(value = "Search a product with an ID", response = FacilityMaintenance.class)
	@RequestMapping(value = "/searchby", method = RequestMethod.GET)
	Page<KiosksMaintenance> facilityMaintenanceList(@RequestParam("name") String query, Pageable pageable) {
		Page<KiosksMaintenance> faciltiy = kiosksMaintenanceRepository.findAll(pageable);
		return faciltiy;
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public Iterable<KiosksMaintenance> kiosksMaintenanceGet() {
		logger.debug("GET endpoint: /api/v1/kiosks");
		return kiosksMaintenanceRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Object> kiosksMaintenanceCreate(@RequestBody KiosksMaintenance kiosksMaintenance) {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		kiosksMaintenanceRepository.save(kiosksMaintenance);
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CREATED);
		logger.info("get kiosksMaintenance Id received: " + kiosksMaintenance.getKiosksId());
		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Object> kiosksMaintenanceUpdate(@PathVariable String id,
			@RequestBody KiosksMaintenance kiosksMaintenance) {
		Optional<KiosksMaintenance> kiosk = kiosksMaintenanceRepository.findById(id);
		if (kiosk.isPresent()) {
			KiosksMaintenance kisokObject = kiosk.get();
			if (kisokObject.getKioskName() != null)
				kisokObject.setKioskName(kiosksMaintenance.getKioskName());
			kiosksMaintenanceRepository.save(kisokObject);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Object> kiosksMaintenanceDelete(@PathVariable String id) {
		Optional<KiosksMaintenance> kiosk = kiosksMaintenanceRepository.findById(id);
		if (kiosk.isPresent()) {
			KiosksMaintenance kisokObject = kiosk.get();
			kiosksMaintenanceRepository.delete(kisokObject);
			logger.debug("{} called endpoint: DELETE /api/v1/kiosks/{id}", kisokObject.getKiosksId(),
					kisokObject.getKioskName());
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public KiosksMaintenance kioskMaintenanceIdGet(@PathVariable String id) {
		Optional<KiosksMaintenance> kiosk = this.kiosksMaintenanceRepository.findById(id);
		logger.debug("{} called endpoint: GET /api/v1/kiosks/{id}");
		return kiosk.get();
	}
}
