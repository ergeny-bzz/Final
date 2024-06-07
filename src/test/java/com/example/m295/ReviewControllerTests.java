package com.example.m295;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewControllerTests {
    private static final String BASE_URL = "http://localhost:8080/yunus/resources/api/reviews";

    private String getAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        return "Basic " + new String(encodedAuth);
    }

    // Positive Test for deleteByDate
    @Test
    @Order(96)
    public void givenDeleteByDate_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/delete-by-date/2025-07-18T00:00:00");
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for deleteByDate


    // Edge Test for deleteByDate
    @Test
    @Order(3)
    public void givenDeleteByDate_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/delete-by-date/2025-06-01T10:00:00");
        String authHeader = getAuthHeader("admin", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for findAllByRecommend
    @Test
    @Order(4)
    public void givenFindAllByRecommend_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/recommend/true");
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for findAllByRecommend


    // Edge Test for findAllByRecommend
    @Test
    @Order(6)
    public void givenFindAllByRecommend_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/recommend/true");
        String authHeader = getAuthHeader("user", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for getTripsByDestination
    @Test
    @Order(7)
    public void givenGetTripsByDestination_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/review/good");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for getTripsByDestination
    @Test
    @Order(8)
    public void givenGetTripsByDestination_whenNoReviewsFound_thenResponseNoContent() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/review/unknown");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NO_CONTENT, httpResponse.getStatusLine().getStatusCode());
    }



    // Positive Test for checkExistenceById
    @Test
    @Order(10)
    public void givenCheckExistenceById_whenReviewExists_thenResponseCodeSuccess() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/exists/1");
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for checkExistenceById
    @Test
    @Order(11)
    public void givenCheckExistenceById_whenReviewDoesNotExist_thenResponseCodeNotFound() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/exists/9999");
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    // Edge Test for checkExistenceById
    @Test
    @Order(12)
    public void givenCheckExistenceById_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/exists/1");
        String authHeader = getAuthHeader("user", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }


    /*
    // Positive Test for executeSql
    @Test
    @Order(13)
    public void givenExecuteSql_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpPost request = new HttpPost(BASE_URL + "/sql");
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for executeSql
    @Test
    @Order(14)
    public void givenExecuteSql_whenServerError_thenResponseCodeInternalServerError() throws IOException {
        HttpPost request = new HttpPost(BASE_URL + "/sql");
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, httpResponse.getStatusLine().getStatusCode());
    }

    // Edge Test for executeSql
    @Test
    @Order(15)
    public void givenExecuteSql_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpPost request = new HttpPost(BASE_URL + "/sql");
        String authHeader = getAuthHeader("admin", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }


     */
    // Positive Test for createMultiple
    @Test
    @Order(16)
    public void givenCreateMultiple_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpPost request = new HttpPost(BASE_URL + "/bulk-create");
        String json = "[{\"reviewId\":1,\"review\":\"Excellent\",\"recommend\":true}]";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for createMultiple


    // Edge Test for createMultiple
    @Test
    @Order(18)
    public void givenCreateMultiple_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpPost request = new HttpPost(BASE_URL + "/bulk-create");
        String json = "[{\"reviewId\":1,\"review\":\"Excellent\",\"recommend\":true}]";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        String authHeader = getAuthHeader("user", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for deleteAll
    @Test
    @Order(99)
    public void givenDeleteAll_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL);
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for deleteAll

    // Edge Test for deleteAll
    @Test
    @Order(98)
    public void givenDeleteAll_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL);
        String authHeader = getAuthHeader("admin", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for getModules
    @Test
    @Order(22)
    public void givenGetModules_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpGet request = new HttpGet(BASE_URL);
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for getModules
    @Test
    @Order(101)
    public void givenGetModules_whenNoModulesFound_thenResponseNoContent() throws IOException {
        HttpGet request = new HttpGet(BASE_URL);
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NO_CONTENT, httpResponse.getStatusLine().getStatusCode());
    }

    // Edge Test for getModules
    @Test
    @Order(24)
    public void givenGetModules_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpGet request = new HttpGet(BASE_URL);
        String authHeader = getAuthHeader("user", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for get
    @Test
    @Order(25)
    public void givenGet_whenReviewExists_thenResponseCodeSuccess() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/1");
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for get
    @Test
    @Order(26)
    public void givenGet_whenReviewDoesNotExist_thenResponseCodeNotFound() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/9999");
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    // Edge Test for get
    @Test
    @Order(27)
    public void givenGet_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/1");
        String authHeader = getAuthHeader("user", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for create
    @Test
    @Order(28)
    public void givenCreate_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpPost request = new HttpPost(BASE_URL);
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        String json = "{\"reviewId\":1,\"review\":\"Excellent\",\"recommend\":true}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for create
    @Test
    @Order(29)
    public void givenCreate_whenWrongJSON_thenResponseCodeBadRequest() throws IOException {
        HttpPost request = new HttpPost(BASE_URL);
        String authHeader = getAuthHeader("user", "123");
        request.addHeader("Authorization", authHeader);
        String json = "{\"eee\":1,\"review\":\"Excellent\",\"recommend\":true}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse.getStatusLine().getStatusCode());
    }

    // Edge Test for create
    @Test
    @Order(30)
    public void givenCreate_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpPost request = new HttpPost(BASE_URL);
        String authHeader = getAuthHeader("user", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        String json = "{\"reviewId\":1,\"review\":\"Excellent\",\"recommend\":true}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for update
    @Test
    @Order(31)
    public void givenUpdate_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpPut request = new HttpPut(BASE_URL);
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        String json = "{\"reviewId\":1,\"review\":\"Updated review\",\"recommend\":false}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }



    // Negative Test for update
    @Test
    @Order(32)
    public void givenUpdate_whenReviewDoesNotExist_thenResponseCodeNotFound() throws IOException {
        HttpPut request = new HttpPut(BASE_URL);
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        String json = "{\"reviewId\":9999,\"review\":\"Updated review\",\"recommend\":false}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    // Edge Test for update
    @Test
    @Order(33)
    public void givenUpdate_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpPut request = new HttpPut(BASE_URL);
        String json = "{\"reviewId\":26,\"review\":\"Updated review\",\"recommend\":false}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        String authHeader = getAuthHeader("admin", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }

    // Positive Test for delete
    @Test
    @Order(90)
    public void givenDelete_whenReviewExists_thenResponseCodeSuccess() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/2");
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    // Negative Test for delete
    @Test
    @Order(89)
    public void givenDelete_whenReviewDoesNotExist_thenResponseCodeNotFound() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/9999");
        String authHeader = getAuthHeader("admin", "1234");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    // Edge Test for delete
    @Test
    @Order(88)
    public void givenDelete_whenAuthFails_thenResponseUnauthorized() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/2");
        String authHeader = getAuthHeader("admin", "wrongpassword");
        request.addHeader("Authorization", authHeader);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, httpResponse.getStatusLine().getStatusCode());
    }
}
