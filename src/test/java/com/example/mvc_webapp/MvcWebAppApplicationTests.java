package com.example.mvc_webapp;

import com.example.mvc_webapp.service.ModulController;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MvcWebAppApplicationTests {

    private static final String BASE_URL = "http://localhost:8080/yunu/resources/api/modules";



    @Test
    public void givenCreateModule_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpPost request = new HttpPost(BASE_URL);
        String json = "{\"modulNr\":2925,\"aktiv\":true,\"kosten\":25.0,\"bezeichnung\":\"Backend\",\"ende\":\"2025-05-29T00:00:00\",\"start\":\"2024-06-07T00:00:00\"}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenGetModuleByID_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/300");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenGetModuleByID_whenBadRequest_thenResponseCodeNotFound() throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/3030303");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenUpdateModule_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpPut request = new HttpPut(BASE_URL);
        String json = "{\"modulNr\":296,\"aktiv\":false,\"kosten\":30.0,\"bezeichnung\":\"Backend Updated\",\"ende\":\"2024-05-30T00:00:00\",\"start\":\"2024-06-08T00:00:00\"}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenUpdateModule_whenBadRequest_thenResponseCodeNotFound() throws IOException {
        HttpPut request = new HttpPut(BASE_URL);
        String json = "{\"modulNr\":99999,\"aktiv\":false,\"kosten\":30.0,\"bezeichnung\":\"Non-Existent Module\",\"ende\":\"2024-05-30T00:00:00\",\"start\":\"2024-06-08T00:00:00\"}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenDeleteModule_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/300");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenDeleteModule_whenBadRequest_thenResponseCodeNotFound() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/99999");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenGetAllModules_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        HttpGet request = new HttpGet(BASE_URL);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenCreateModule_whenInvalidData_thenResponseCodeBadRequest() throws IOException {
        HttpPost request = new HttpPost(BASE_URL);
        String json = "{\"modulNr\":\"invalid\",\"aktiv\":\"not a boolean\",\"kosten\":\"invalid\",\"bezeichnung\":null,\"ende\":\"invalid date\",\"start\":\"invalid date\"}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenCreateModule_whenMissingFields_thenResponseCodeBadRequest() throws IOException {
        HttpPost request = new HttpPost(BASE_URL);
        String json = "{\"modulNr\":2926}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse.getStatusLine().getStatusCode());
    }


}
