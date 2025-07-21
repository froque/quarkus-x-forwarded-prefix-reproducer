package org.acme;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class RedirectResourceTest {

    @Test
    void testRedirectLocation() {

		given().redirects().follow(false)
			   .when().get("/old-path/location")
			   .then()
			   		.statusCode(302)
			   		.header("Location", "http://localhost:8081/some-root-path/new-path");
    }

    @Test
    void testRedirectContentLocation() {

		given().redirects().follow(false)
			   .when().get("/old-path/content-location")
			   .then()
			   		.statusCode(302)
			   		.header("Content-Location", "http://localhost:8081/new-path");
    }

    @Test
    void testRedirectLocationWithProxyHeaders() {

		given().redirects().follow(false)
			   .when()
			   		.header("X-Forwarded-Port", "81")
			   		.header("X-Forwarded-Proto", "https")
			   		.header("X-Forwarded-Host", "example.com")
			   		.header("X-Forwarded-Prefix", "/some-forwarded-prefix")
			   		.get("/old-path/location")
			   .then()
			   		.statusCode(302)
			   		.header("Location", "https://example.com:81/some-forwarded-prefix/some-root-path/new-path");
    }

    @Test
    void testRedirectContentLocationWithProxyHeaders() {

		given().redirects().follow(false)
			   .when()
			   		.header("X-Forwarded-Port", "81")
			   		.header("X-Forwarded-Proto", "https")
			   		.header("X-Forwarded-Host", "example.com")
			   		.header("X-Forwarded-Prefix", "/some-forwarded-prefix")
			   .when().get("/old-path/content-location")
			   .then()
			   		.statusCode(302)
			   		.header("Content-Location", "https://example.com:81/some-forwarded-prefix/new-path");
    }

}
