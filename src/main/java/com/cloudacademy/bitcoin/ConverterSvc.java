package com.cloudacademy.bitcoin;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.NumberFormat;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConverterSvc 
{
    private final String BITCOIN_CURRENTPRICE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private final HttpGet httpget = new HttpGet(BITCOIN_CURRENTPRICE_URL);

    private CloseableHttpClient httpclient;

    /*
    //curl -s https://api.coindesk.com/v1/bpi/currentprice.json | jq .
    //example json response from coindesk api:
    {
        "time": {
            "updated": "Oct 15, 2020 22:55:00 UTC",
            "updatedISO": "2020-10-15T22:55:00+00:00",
            "updateduk": "Oct 15, 2020 at 23:55 BST"
        },
        "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD)",
        "chartName": "Bitcoin",
        "bpi": {
            "USD": {
            "code": "USD",
            "symbol": "&#36;",
            "rate": "11,486.5341",
            "description": "United States Dollar",
            "rate_float": 11486.5341
            },
            "GBP": {
            "code": "GBP",
            "symbol": "&pound;",
            "rate": "8,900.8693",
            "description": "British Pound Sterling",
            "rate_float": 8900.8693
            },
            "EUR": {
            "code": "EUR",
            "symbol": "&euro;",
            "rate": "9,809.3278",
            "description": "Euro",
            "rate_float": 9809.3278
            }
        }
    }
    */

    public ConverterSvc() {
         this.httpclient = HttpClients.createDefault();
    }

    public double getExchangeRate(String currency) {
        double rate = 0;

        try {
            CloseableHttpResponse response = this.httpclient.execute(httpget);

            InputStream inputStream = response.getEntity().getContent();
            var json = new BufferedReader(new InputStreamReader(inputStream));

            @SuppressWarnings("deprecation")
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            String n = jsonObject.get("bpi").getAsJsonObject().get(currency).getAsJsonObject().get("rate").getAsString();
            NumberFormat nf = NumberFormat.getInstance();
            rate = nf.parse(n).doubleValue();
        } catch (Exception ex) {
            rate = -1;
        }

        return rate;
    }

    public double convertBitcoins(String currency, int coins) {
        double dollars = 0;

        var exchangeRate = getExchangeRate(currency);

        dollars = exchangeRate * coins;

        return dollars;
    }
}
