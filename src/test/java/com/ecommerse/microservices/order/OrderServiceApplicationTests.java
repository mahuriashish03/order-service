package com.ecommerse.microservices.order;

import com.ecommerse.microservices.order.stubs.InventoryClientStup;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer();

	@LocalServerPort
	private Integer port;

	static {
		mySQLContainer.start();
	}
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;

	}
//	@Test
//	void createNewOrder() {
//		String submitOrder = """
//				{
//					"orderNumber": "0f093549-5ed0-4357-a382-7e4d29d6bb80"
//				    "skuCode": "IPHONE_16",
//				    "price": 1500,
//				    "quantity": 1
//				}
//				""";
//		InventoryClientStup.stubInventoryCall("IPHONE_16", 1);
//		String responseString = RestAssured.given()
//				.contentType("application/json")
//				.body(submitOrder)
//				.when()
//				.post("api/order")
//				.then()
//				.statusCode(201)
//				.extract()
//				.body().asString();
//		assertThat(responseString, Matchers.is("Order created successfully"));
//	}

}
