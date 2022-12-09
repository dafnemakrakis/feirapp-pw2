package demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
    LoginController loginController;

	@Test
	void responseCodeShouldBeOk() {
		// Specify the base URL to the RESTful web service
		//RestAssured.baseURI = "http://localhost:8070";
		// Get the RequestSpecification of the request to be sent to the server
		RestAssured.given().get("/").then().assertThat().statusCode(200);
		
	   
	}

}
