package com.cloudacademy.bitcoin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Unit test for simple ConverterSvc.
 */
public class ConverterSvcTest 
{
    private static final double DELTA = 0;

    private CloseableHttpClient client;
    private CloseableHttpResponse response;
    private StatusLine statusLine;
    private HttpEntity entity;
    private InputStream stream;

    private ConverterSvc converterSvc;

    @BeforeEach
    public void setUp() {
        client = mock(CloseableHttpClient.class);
        response = mock(CloseableHttpResponse.class);
        statusLine = mock(StatusLine.class);
        entity = mock(HttpEntity.class);

        stream = new ByteArrayInputStream("{\"time\": {\"updated\": \"Oct 15, 2020 22:55:00 UTC\",\"updatedISO\": \"2020-10-15T22:55:00+00:00\",\"updateduk\": \"Oct 15, 2020 at 23:55 BST\"},\"chartName\": \"Bitcoin\",\"bpi\": {\"USD\": {\"code\": \"USD\",\"symbol\": \"&#36;\",\"rate\": \"11,486.5341\",\"description\": \"United States Dollar\",\"rate_float\": 11486.5341},\"GBP\": {\"code\": \"GBP\",\"symbol\": \"&pound;\",\"rate\": \"8,900.8693\",\"description\": \"British Pound Sterling\",\"rate_float\": 8900.8693},\"EUR\": {\"code\": \"EUR\",\"symbol\": \"&euro;\",\"rate\": \"9,809.3278\",\"description\": \"Euro\",\"rate_float\": 9809.3278}}}".getBytes());
        converterSvc = new ConverterSvc(client);
    }

    @Test
    public void shouldReturnUSDExchangeRate() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);

        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.GetExchangeRate(ConverterSvc.Currency.USD);

        //assert
        double expected = 11486.5341;
        Assertions.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldReturnGBPExchangeRate() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.GetExchangeRate(ConverterSvc.Currency.GBP);
    
        //assert
        double expected = 8900.8693;
        Assertions.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldConvert1BitcoinToUSD() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.ConvertBitcoins(ConverterSvc.Currency.USD, 1);
    
        //assert
        double expected = 11486.5341;
        Assertions.assertEquals(expected, actual, DELTA);
    }
    
    @Test
    public void shouldConvert2BitcoinsToUSD() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.ConvertBitcoins(ConverterSvc.Currency.USD, 2);
    
        //assert
        double expected = 22973.0682;
        Assertions.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldConvert0BitcoinsTo0USD() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.ConvertBitcoins(ConverterSvc.Currency.USD, 0);
    
        //assert
        double expected = 0;
        Assertions.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldThrowExceptionWhenBitcoinsLessThanZero() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        Assertions.assertThrows(IllegalArgumentException.class, () -> converterSvc.ConvertBitcoins(ConverterSvc.Currency.USD, -1));
    }

    @Test
    public void shouldReturnNegative1WhenServiceUnavailable() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(503);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.ConvertBitcoins(ConverterSvc.Currency.USD, 2);
    
        //assert
        double expected = -1;
        Assertions.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldReturnNegative1WhenExecuteThrowsIOException() throws IOException {
        when(client.execute(any(HttpGet.class))).thenThrow(IOException.class);
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.GetExchangeRate(ConverterSvc.Currency.USD);
    
        //assert
        double expected = -1;
        Assertions.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldReturnNegative1WhenClosingResponseThrowsIOException() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);

        doThrow(IOException.class).when(response).close();
    
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.GetExchangeRate(ConverterSvc.Currency.USD);
    
        //assert
        double expected = -1;
        Assertions.assertEquals(expected, actual, DELTA);
    }
}
