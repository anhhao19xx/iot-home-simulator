package com.example.iothomeremote.adapters;

import android.util.Log;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class MyWebSocketClient extends WebSocketClient {
    public static MyWebSocketClient client;

    public MyWebSocketClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i(this.getClass().getName(), "Connected");
    }

    @Override
    public void onMessage(String message) {
        Log.i(this.getClass().getName(), "received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(this.getClass().getName(), "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

}
