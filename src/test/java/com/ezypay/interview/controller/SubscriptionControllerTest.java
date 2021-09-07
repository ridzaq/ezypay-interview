package com.ezypay.interview.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ezypay.interview.object.Invoice;
import com.ezypay.interview.object.Subscribe;
import com.ezypay.interview.object.Type;
import com.ezypay.interview.service.SubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	SubscriptionService subService;
	//150.50, Type.DAILY, null, "06/09/2021", "06/10/2021"

    @Autowired
    ObjectMapper mapper;
    
	@Test
	public void createInvoice_Daily() throws Exception {
		Subscribe SUBSCRIBE = new Subscribe(150.50, Type.DAILY, null, "06/09/2021", "16/09/2021");
		List<String> invoiceDates = new ArrayList<String>() {{add( "06/09/2021");add( "07/09/2021");
		add( "08/09/2021");add( "09/09/2021");add( "10/09/2021");add( "11/09/2021");add( "12/09/2021");
		add( "13/09/2021");add( "14/09/2021");add( "15/09/2021");add( "16/09/2021");}};
		
		Invoice INVOICE = new Invoice(150.50,Type.DAILY, invoiceDates);

		when(subService.getInvoice(SUBSCRIBE)).thenReturn(INVOICE);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/subscribe")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(SUBSCRIBE));
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void createInvoice_Weekly() throws Exception {
		Subscribe SUBSCRIBE = new Subscribe(150.50, Type.WEEKLY, "TUESDAY", "06/09/2021", "30/09/2021");
		List<String> invoiceDates = new ArrayList<String>() {{add( "07/09/2021");add( "14/09/2021");
		add( "21/09/2021");add( "28/09/2021");}};
		
		Invoice INVOICE = new Invoice(150.50,Type.WEEKLY, invoiceDates);

		when(subService.getInvoice(SUBSCRIBE)).thenReturn(INVOICE);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/subscribe")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(SUBSCRIBE));
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void createInvoice_Monthly() throws Exception {
		Subscribe SUBSCRIBE = new Subscribe(150.50, Type.MONTHLY, "10", "06/09/2021", "06/11/2021");
		List<String> invoiceDates = new ArrayList<String>() {{add( "10/09/2021");add( "10/10/2021");
		add( "10/11/2021");}};
		
		Invoice INVOICE = new Invoice(150.50,Type.WEEKLY, invoiceDates);

		when(subService.getInvoice(SUBSCRIBE)).thenReturn(INVOICE);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/subscribe")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(SUBSCRIBE));
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
	}
}
