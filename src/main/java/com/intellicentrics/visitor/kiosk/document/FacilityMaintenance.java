package com.intellicentrics.visitor.kiosk.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="facilitymaintenance")
public class FacilityMaintenance {
	@Id
	private  String facilityId;
	 @ApiModelProperty(notes = "The name of facility")
	private String facilityName;
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public FacilityMaintenance(String facilityId, String facilityName) {
		super();
		this.facilityId = facilityId;
		this.facilityName = facilityName;
	}
	@Override
	public String toString() {
		return "FacilityMaintenance [facilityId=" + facilityId + ", facilityName=" + facilityName + "]";
	}
		
}
