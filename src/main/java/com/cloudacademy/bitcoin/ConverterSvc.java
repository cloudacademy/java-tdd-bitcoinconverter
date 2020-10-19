package com.cloudacademy.bitcoin;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.NumberFormat;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.ByteArrayInputStream;

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

    public ConverterSvc(CloseableHttpClient httpClient) {
        this.httpclient = httpClient;
    }

    public enum Currency {
        USD,
        GBP,
        EUR
    }

    public double GetExchangeRate(Currency currency) {
        double rate = 0;

        try (CloseableHttpResponse response = this.httpclient.execute(httpget)) {
            switch (response.getStatusLine().getStatusCode()) {
                case 200:
                    HttpEntity entity = response.getEntity();

                    InputStream inputStream = response.getEntity().getContent();
                    var json = new BufferedReader(new InputStreamReader(inputStream));

                    @SuppressWarnings("deprecation")
                    JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                    String n = jsonObject.get("bpi").getAsJsonObject().get(currency.toString()).getAsJsonObject().get("rate").getAsString();
                    NumberFormat nf = NumberFormat.getInstance();
                    rate = nf.parse(n).doubleValue();

                    break;
                default:
                    rate = -1;
            }
        } catch (IOException | ParseException ex) {
            rate = -1;
        }

        return rate;
    }

    public double ConvertBitcoins(Currency currency, double coins) throws IllegalArgumentException {
        double dollars = 0;

        if(coins < 0) {
            throw new IllegalArgumentException("Number of coins must not be less than zero"); 
        }

        var exchangeRate = GetExchangeRate(currency);

        if(exchangeRate >= 0) {
            dollars = exchangeRate * coins;
        } else {
            dollars = -1;
        }

        return dollars;
    }
}
