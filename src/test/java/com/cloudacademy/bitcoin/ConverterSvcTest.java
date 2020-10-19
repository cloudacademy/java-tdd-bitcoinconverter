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

    @Test
    public void shouldReturnUSDExchangeRate() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.GetExchangeRate("USD");

        //assert
        double expected = 100;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnGBPExchangeRate() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.GetExchangeRate("GBP");

        //assert
        double expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEURExchangeRate() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.GetExchangeRate("EUR");

        //assert
        double expected = 300;
        Assertions.assertEquals(expected, actual);
    }    
    
    @Test
    public void shouldConvert1BitcoinToUSD() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.ConvertBitcoins("USD", 1);
    
        //assert
        double expected = 100;
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void shouldConvert2BitcoinsToUSD() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.ConvertBitcoins("USD", 2);
    
        //assert
        double expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldConvert1BitcoinToGBP() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.ConvertBitcoins("GBP", 1);
    
        //assert
        double expected = 200;
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void shouldConvert2BitcoinsToGBP() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.ConvertBitcoins("GBP", 2);
    
        //assert
        double expected = 400;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldConvert1BitcoinToEUR() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.ConvertBitcoins("EUR", 1);
    
        //assert
        double expected = 300;
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void shouldConvert2BitcoinsToEUR() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.ConvertBitcoins("EUR", 2);
    
        //assert
        double expected = 600;
        Assertions.assertEquals(expected, actual);
    }

}
