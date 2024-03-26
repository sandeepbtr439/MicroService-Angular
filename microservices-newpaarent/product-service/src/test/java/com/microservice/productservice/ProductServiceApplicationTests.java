package com.microservice.productservice;//package com.microservice.productservice;
//
//import com.microservice.productservice.Repository.ProductRepository;
//import com.microservice.productservice.dto.ProductRequest;
//import com.microservice.productservice.dto.ProductResponse;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//import org.testcontainers.utility.DockerImageName;
//
//import java.math.BigDecimal;
//
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//	@Autowired
//	private MockMvc mockMvc;
//	@Autowired
//	private ObjectMapper objectMapper;
//	@Autowired
//	private ProductRepository productRepository;
//
//	@Container   //CREATING A MONGO CONTAINER WHICH IS A DOCKER IMAGE AND ACTS AS A MOCK DATABASE
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//	static {
//		mongoDBContainer.start();
//	}
//@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//	@Test
//	void shouldCreateProduct() throws Exception {
//	ProductRequest productRequest=getProductRequest();
//	String productReq =objectMapper.writeValueAsString(productRequest);
//	mockMvc.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON).content(productReq)).andExpect(status().isCreated());
//		Assertions.assertEquals(1, productRepository.findAll().size());
//	}
//
//private ProductRequest getProductRequest() {
//	return ProductRequest.builder().name("iphone").description("it is fifteen").price(BigDecimal.valueOf(1500)).build();
//}
//// void shouldGetProducts() throws JsonProcessingException {
////	ProductResponse productResponse = getProductResponse();
////
//////	String ProductResponse= objectMapper.writeValueAsString(productResponse);
////	mockMvc.perform(MockMvcRequestBuilders.get("/api/product").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(productResponse.getName())
//// }
//// private ProductResponse getProductResponse(){
////	return ProductResponse.builder().name("iphone").description("iphone15").price(BigDecimal.valueOf(1500)).build();
//// }
//}
