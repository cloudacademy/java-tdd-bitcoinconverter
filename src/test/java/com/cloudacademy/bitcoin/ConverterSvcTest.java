package com.cloudacademy.bitcoin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple ConverterSvc.
 */
public class ConverterSvcTest 
{
    @Test
    public void getExchangeRate_USD_ReturnsUSDExchangeRate() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.getExchangeRate("USD");

        //assert
        double expected = 100;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getExchangeRate_GBP_ReturnsGBPExchangeRate() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.getExchangeRate("GBP");

        //assert
        double expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getExchangeRate_EUR_ReturnsEURExchangeRate() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.getExchangeRate("EUR");

        //assert
        double expected = 300;
        Assertions.assertEquals(expected, actual);
    }    
    
    @Test
    public void convertBitcoins_1BitCoinToUSD_ReturnsUSDDollars() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.convertBitcoins("USD", 1);
    
        //assert
        double expected = 100;
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void convertBitcoins_2BitCoinToUSD_ReturnsUSDDollars() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.convertBitcoins("USD", 2);
    
        //assert
        double expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_1BitCoinToGBP_ReturnsGBPDollars() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.convertBitcoins("GBP", 1);
    
        //assert
        double expected = 200;
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void convertBitcoins_2BitCoinToGBP_ReturnsGBPDollars() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.convertBitcoins("GBP", 2);
    
        //assert
        double expected = 400;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_1BitCoinToEUR_ReturnsEURDollars() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.convertBitcoins("EUR", 1);
    
        //assert
        double expected = 300;
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void convertBitcoins_2BitCoinToEUR_ReturnsEURDollars() {
        //act
        ConverterSvc converterSvc = new ConverterSvc();
        var actual = converterSvc.convertBitcoins("EUR", 2);
    
        //assert
        double expected = 600;
        Assertions.assertEquals(expected, actual);
    }

}
