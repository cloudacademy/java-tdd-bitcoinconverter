package com.cloudacademy.bitcoin.client;

import com.cloudacademy.bitcoin.ConverterSvc;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class BitcoinApp 
{
    public static void main( String[] args )
    {
        var converterSvc = new ConverterSvc();

        while (true) {
            try {
                var coins = Double.parseDouble(System.console().readLine("Coins: "));
                var currency = System.console().readLine("Currency (USD, GBP, or EUR): ");
                var dollars = converterSvc.ConvertBitcoins(ConverterSvc.Currency.valueOf(currency), coins);

                NumberFormat df = new DecimalFormat("#0.0000");
                df.setRoundingMode(RoundingMode.CEILING);

                System.out.println( String.format("%s Bitcoin = %s %s", coins, df.format(dollars), currency));
            }
            catch (Exception ex) {
                //swallow
            }
        }
    }
}