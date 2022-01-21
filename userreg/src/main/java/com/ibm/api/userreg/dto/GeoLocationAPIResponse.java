package com.ibm.api.userreg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoLocationAPIResponse {
	
	String status;
	String country;
	String countryCode;
	String region;
	String regionName;
	String city;
	String zip;
	String timezone;
	String isp;

}
