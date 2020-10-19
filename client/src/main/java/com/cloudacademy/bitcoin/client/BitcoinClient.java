package com.cloudacademy.bitcoin;

import com.cloudacademy.bitcoin.ConverterSvc;

public class BitcoinClient 
{
    public static void main( String[] args )
    {
        var converterSvc = new ConverterSvc();

        while (true) {
            try {
                var coins = Double.parseDouble(System.console().readLine("Coins: "));
                var currency = System.console().readLine("Currency (USD, GBP, or EUR): ");
                var dollars = converterSvc.ConvertBitcoins(ConverterSvc.Currency.valueOf(currency), coins);
                System.out.println( String.format("%s Bitcoin = %s %s", coins, dollars, currency));
            }
            catch (Exception ex) {
                //swallow
            }
        }
    }
}
