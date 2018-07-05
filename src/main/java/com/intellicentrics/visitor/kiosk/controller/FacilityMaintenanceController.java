package com.intellicentrics.visitor.kiosk.controller;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.intellicentrics.visitor.kiosk.document.FacilityMaintenance;
import com.intellicentrics.visitor.kiosk.exception.EntityNotFoundException;
import com.intellicentrics.visitor.kiosk.exception.NotFoundException;
import com.intellicentrics.visitor.kiosk.repository.FacilityMaintenanceRepository;
import com.intellicentrics.visitor.kiosk.service.FacilityMaintenanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/facilities")
@Api(value = "onlinestore", description = "Operations pertaining for Facility Maintenance ")
public class FacilityMaintenanceController {
	@Autowired
	private FacilityMaintenanceRepository facilityMaintenanceRepository;
	@Autowired
	private FacilityMaintenanceService facilityMaintenanceService;

	private final Logger logger = LoggerFactory.getLogger(FacilityMaintenanceController.class);

	@ApiOperation(value = "Search a product with an ID", response = FacilityMaintenance.class)
	@RequestMapping(value = "/searchby", method = RequestMethod.GET)
	Page<FacilityMaintenance> facilityMaintenanceList(@RequestParam("name") String query, Pageable pageable) {
		Page<FacilityMaintenance> faciltiy = facilityMaintenanceRepository.findAll(pageable);
		return faciltiy;
	}

	@ApiOperation(value = "View a list of available facilities", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	
	@RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
	public Iterable<FacilityMaintenance> facilityMaintenanceGet() throws EntityNotFoundException{
		logger.debug("GET endpoint: /api/v1/facilities");
		return facilityMaintenanceRepository.findAll();
	}

	@ApiOperation(value = "Add a facility")
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<FacilityMaintenance> facilityMaintenanceCreate(
			@RequestBody FacilityMaintenance facilityMaintenance, UriComponentsBuilder uriBuilder) throws EntityNotFoundException {

		URI location = uriBuilder.path("api/v1/facilities/{id}").buildAndExpand(facilityMaintenance.getFacilityId())
				.toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		Logger logger = LoggerFactory.getLogger(this.getClass());

		facilityMaintenanceRepository.save(facilityMaintenance);

		logger.info("getfacilityMaintenance Id received: " + facilityMaintenance.getFacilityId());
		return new ResponseEntity<>(facilityMaintenance, headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a facility")
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Object> facilityMaintenanceUpdate(@PathVariable String id,
			@RequestBody FacilityMaintenance facilityMaintenance) throws EntityNotFoundException {
		Optional<FacilityMaintenance> facility = facilityMaintenanceService.findByfacilityId(id);
		if (facility.isPresent()) {
			FacilityMaintenance facilityObject = facility.get();
			if (facilityMaintenance.getFacilityName() != null)
				facilityObject.setFacilityName(facilityMaintenance.getFacilityName());
			facilityMaintenanceRepository.save(facilityObject);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a facility")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Object> facilityMaintenanceDelete(@PathVariable String id) throws EntityNotFoundException{
		Optional<FacilityMaintenance> facility = facilityMaintenanceService.findByfacilityId(id);
		if (facility.isPresent()) {
			FacilityMaintenance facilityObjec = facility.get();
			facilityMaintenanceService.deleteByfaclilityId(facilityObjec.getFacilityId());
			logger.debug("{} called endpoint: DELETE /api/v1/facilities/{id}", facilityObjec.getFacilityId(),
					facilityObjec.getFacilityName());
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public FacilityMaintenance facilityMaintenanceIdGet(@PathVariable String id)throws EntityNotFoundException {

		Optional<FacilityMaintenance> facility = this.facilityMaintenanceService.findByfacilityId(id);
		logger.debug("{} called endpoint: GET /api/v1/facilities/{id}");
		return facility.get();
	}
}
