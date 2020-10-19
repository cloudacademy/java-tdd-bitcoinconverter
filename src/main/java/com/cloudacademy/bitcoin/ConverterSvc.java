package com.cloudacademy.bitcoin;

public class ConverterSvc 
{
    public ConverterSvc() {
    }

    public int GetExchangeRate(String currency) {
        if(currency.equals("USD")) {
            return 100;
        }
        else if (currency.equals("GBP")) {
            return 200;
        }
        else if (currency.equals("EUR")) {
            return 300;
        }

        return 0;
    }

    public double ConvertBitcoins(String currency, int coins) {
        double dollars = 0;

        var exchangeRate = GetExchangeRate(currency);

        dollars = exchangeRate * coins;

        return dollars;
    }
}
