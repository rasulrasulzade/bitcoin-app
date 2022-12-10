package com.example.bitcoinapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

public class BitcoinApplication extends Application {
    public LocalDate parseLocalDate(long time) {
        long epoch = Instant.ofEpochSecond(time).toEpochMilli();
        return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public BitcoinData getBitcoinData() throws IOException {
        URL url = new URL("https://api.blockchain.info/charts/market-price?timespan=1weeks&format=json");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        System.out.println("FETCHING DATA...");
        urlConnection.connect();

        if(urlConnection.getResponseCode() != 200)
            throw new RuntimeException("HttpResponseCode: " + urlConnection.getResponseCode());

        StringBuilder information = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()){
            information.append(scanner.nextLine());
        }
        scanner.close();

        System.out.println(information);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(information.toString(), BitcoinData.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        BitcoinData bitcoinData = getBitcoinData();
        List<BitcoinDataItem> bitcoinDataItems = bitcoinData.getValues();


        LocalDate startDate = parseLocalDate(bitcoinDataItems.get(0).getX());
        LocalDate endDate = parseLocalDate(bitcoinDataItems.get(bitcoinDataItems.size() - 1).getX());

        // create the x and y axes that the chart is going to use
        NumberAxis xAxis= new NumberAxis(3, 10, 1);
        NumberAxis yAxis = new NumberAxis();

        // set the titles for the axes
        xAxis.setLabel("Date");
        yAxis.setLabel("Bitcoin price (USD)");

        // create the line chart. The values of the chart are given as numbers
        // and it uses the axes we created earlier
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Relative support in "+ startDate.getDayOfMonth() + " " + startDate.getMonth() + " - " + endDate.getDayOfMonth() + " " + endDate.getMonth());

        // create the data set that is going to be added to the line chart
        XYChart.Series chartData = new XYChart.Series();
        chartData.setName("Bitcoin prices");

        for(BitcoinDataItem item: bitcoinDataItems){
            LocalDate date = parseLocalDate(item.getX());
            chartData.getData().add(new XYChart.Data(date.getDayOfMonth(), item.getY()));
        }

        // add the data set to the line chart
        lineChart.getData().add(chartData);

        // display the line chart
        Scene view = new Scene(lineChart, 1500, 800);
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}