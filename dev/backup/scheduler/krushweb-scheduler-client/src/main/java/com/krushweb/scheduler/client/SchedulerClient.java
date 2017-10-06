package com.krushweb.scheduler.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SchedulerClient extends BaseClient {

	private final String serviceNameRoot = "/scheduler";
	private final String serviceNameRegister = "/scheduler/register";

        @Value("${krushweb.scheduler.onboard_flux_id:1234}")
        private Integer onboardFluxID

	private RestTemplate restTemplate = new RestTemplate();

	private SchedulerClient() {}

	@Autowired
	public SchedulerClient(@Value("${krushweb.scheduler.url:http://krushweb.scheduler.com/}") String hostAddress,
			@Value("${krushweb.scheduler.version:v1}") String serviceVersion) {
		this.hostAddress = hostAddress;
		this.serviceVersion = "/" + serviceVersion;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getHostRoot());
		restTemplate.getForObject(builder.build().encode().toUri(), String.class);
	}

	private java.net.URI getCompleteUri(UriComponentsBuilder builder){
		return builder.build().encode().toUri();
	}

	public Integer startOnboardFlux(String clientName, String clientEmail, String meetingDate){
		String resourceName = "/"+onboardFluxID+"/start";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				getHostVenda() + resourceName)
				.queryParam("clientName", clientName)
				.queryParam("clientEmail", clientEmail)
				.queryParam("meetingDate", meetingDate);
		return restTemplate.getForObject(getCompleteUri(builder), Integer.class);
	}

	private String getHostRegister() {
		return super.hostAddress + super.serviceVersion + this.serviceNameVenda;
	}

	private String getHostRoot() {
		return super.hostAddress + super.serviceVersion + this.serviceNameRoot;
	}
	
}
