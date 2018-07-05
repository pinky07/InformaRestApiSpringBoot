package com.intellicentrics.visitor.kiosk.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="kiosk_maintenance")
public class KiosksMaintenance {
@Id
	private  String kiosksId;
	private String kioskName;
	public String getKiosksId() {
		return kiosksId;
	}
	public void setKiosksId(String kiosksId) {
		this.kiosksId = kiosksId;
	}
	public String getKioskName() {
		return kioskName;
	}
	public void setKioskName(String kioskName) {
		this.kioskName = kioskName;
	}
	@Override
	public String toString() {
		return "KiosksMaintenance [kiosksId=" + kiosksId + ", kioskName=" + kioskName + "]";
	}
	public KiosksMaintenance(String kiosksId, String kioskName) {
		super();
		this.kiosksId = kiosksId;
		this.kioskName = kioskName;
	}
	
	
}
