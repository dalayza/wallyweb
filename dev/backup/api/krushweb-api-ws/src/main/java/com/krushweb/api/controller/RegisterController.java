package com.krushweb.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.krushweb.scheduler.client.SchedulerClient;

@RestController
@RequestMapping("/krushweb")
public class RegisterController extends BaseController {

	private ApiService apiService;

        private HttpHeaders responseHeaders = new HttpHeaders();

	@Autowired
	public ImpostosController(TaxService taxService) {
		this.taxService = taxService;
        
		// this is for Atlas platform caching...
		responseHeaders.set("Cache-Control", "max-age=150");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String entryPoint() {
		return "Serviço responsável por retornar os impostos de compra e venda. Consulte a documentação da API para os recursos disponíveis.";
	}

	@RequestMapping(value = "/{register}", method = RequestMethod.PUT)
	public HttpEntity<Integer> register(@RequestParam(value = "clientName" , required = true) final String clientName,
	        @RequestParam(value = "clientEmail", required = true) final String clientEmail,
	        @RequestParam(value = "meetingDate", required = true) final String meetingDate)
	        throws Exception {

                apiService.register(clientName,clientEmail,meetingDate);

		return new HttpEntity<Integer>( result.get(0),responseHeaders);
	}
}
