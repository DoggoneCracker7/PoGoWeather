package com.example.kbaran.pogoweather.http_connection;

/**
 * Created by kbaran on 06.12.16.
 */

@FunctionalInterface
public interface HttpConnectionHandler {
    public String connectAndReceiveData(String url);
}
