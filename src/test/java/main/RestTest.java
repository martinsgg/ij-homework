package main;

import java.nio.charset.Charset;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import controllers.CustomerController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CustomerController.class)
public class RestTest {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Autowired
    private MockMvc mockMvc;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testCustomerCreation() throws Exception {
        this.mockMvc.perform(post("/Customer")
                .content("{\"name\":\"Janis\",\"surname\":null,\"email\":\"test@testo.lv\",\"password\":\"550055\",\"personal_id\":\"123456-12345\"}")
                .contentType(contentType))
                .andExpect(status().isOk()); 
    }
    
    @Test
    public void testSameEmailCreation() throws Exception {
    	this.mockMvc.perform(post("/Customer")
                .content("{\"name\":\"Janis\",\"surname\":null,\"email\":\"test@test.lv\",\"password\":\"550055\",\"personal_id\":\"123456-12345\"}")
                .contentType(contentType))
                .andExpect(status().isOk()); 
    	
    	thrown.expect(NestedServletException.class);
    	
		this.mockMvc.perform(post("/register")
		        .content("{\"email\":\"test@test.lv\",\"password\":\"550055\"}")
		        .contentType(contentType));
    }
    
    @Test
    public void testRegistration() throws Exception {
	    this.mockMvc.perform(post("/register")
	            .content("{\"email\":\"test@testx.lv\",\"password\":\"12345\"}")
	            .contentType(contentType))
	            .andExpect(status().isOk());
    }
    
    @Test
    public void testDebtCollectionCaseCreation() throws Exception {
        this.mockMvc.perform(post("/DebtCollectionCase")
                .content("{\"customer_id\":1,\"amount\":50,\"due_date\":\"2017-04-23T18:25:43.511Z\"}")
                .header("Access-Key", "123x55")
                .contentType(contentType))
                .andExpect(status().isOk());    
    }
}
