package com.example.notesapp.infrastructure;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InternetConnectionManager{

//    Context context = InternetConnectionManager.this;
//    WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//    private String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
    private String apiDomain = "http://10.0.2.2:8080/";
    //singleton stuff
    private static InternetConnectionManager instance=new InternetConnectionManager();
    private InternetConnectionManager() {}
    public static InternetConnectionManager getInstance()
    {
        return instance;
    }

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    // POST
    public void callPostEndpoint(String endpointName , String json, OnDataReceived handler )
            throws ExecutionException, InterruptedException , HttpErrorException {
        String url = this.apiDomain+endpointName;
        System.out.println("url is "+url);
        Callable<Void> internetCallable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                String data = postData(url , json);
                handler.dataReceived(data);
                return null;
            }
        };
        executorService.submit(internetCallable);
    }

    private String postData(String endPointURL, String json ) throws IOException, HttpErrorException {
        URL url = new URL(endPointURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println("Calling url : " + url);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            System.out.println("JSON data to be sent : " + json); // print the JSON data before sending
            os.write(input, 0, input.length);
            System.out.println("getOutputStream url : " + os);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code : " + responseCode); // print the response code
        String line = "";
        StringBuilder response = new StringBuilder();
        try {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                try {
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                } finally {
                    reader.close();
                }
                System.out.println("response.toString() ::::::::::::: " + response.toString() );
                return response.toString();
            } else {
                throw new HttpErrorException(responseCode);
            }
        }
        finally {
            connection.disconnect();
        }
    }


    // PUT
    public void callPutEndpoint(String endpointName, String json, OnDataReceived handler)
            throws ExecutionException, InterruptedException, HttpErrorException {
        String url = this.apiDomain + endpointName;
        Callable<Void> internetCallable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                String data = putData(url, json);
                handler.dataReceived(data);
                return null;
            }
        };
        executorService.submit(internetCallable);
    }

    private String putData(String endPointURL, String json) throws IOException, HttpErrorException {
        URL url = new URL(endPointURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println("Calling url : " + url);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("response code is "+ responseCode);
        String line;
        StringBuilder response = new StringBuilder();
        try {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } else {
                throw new HttpErrorException(responseCode);
            }
        } finally {
            connection.disconnect();
        }
        return response.toString();
    }

    // GET
    public void callGetEndpoint(String endpointName, OnDataReceived handler)
            throws ExecutionException, InterruptedException {
        String url = this.apiDomain+endpointName;
        Callable<Void> internetCallable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    String data = getData(url);
                    handler.dataReceived(data);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw e;
                }
                return null;
            }
        };
        executorService.submit(internetCallable);
    }

    private String getData(String endPointURL) throws IOException, HttpErrorException {

        URL url = new URL(endPointURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println("Calling url : " + url);
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        String line = "";
        StringBuilder response = new StringBuilder();
        try {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                try {
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                } finally {
                    reader.close();
                }
                System.out.println("response.toString() ::::::::::::: " + response.toString() );
                return response.toString();
            } else {
                throw new HttpErrorException(responseCode);
            }
        }
        finally {
            connection.disconnect();
        }
    }


    // Delete
    public void callDeleteEndpoint(String endpointName, OnDataReceived handler)
            throws ExecutionException, InterruptedException {
        String url = this.apiDomain+endpointName;
        Callable<Void> internetCallable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    String data = deleteData(url);
                    handler.dataReceived(data);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw e;
                }
                return null;
            }
        };
        executorService.submit(internetCallable);
    }

    private String deleteData(String endPointURL) throws IOException, HttpErrorException {
        URL url = new URL(endPointURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println("Calling url : " + url);
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        String line = "";
        StringBuilder response = new StringBuilder();
        try {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "OK";
            } else {
                throw new HttpErrorException(responseCode);
            }
        }
        finally {
            connection.disconnect();
        }
    }


}

