package com.afcat.channel.mqtttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity implements MqttCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connect(View view) {
        String tmpDir = System.getProperty("java.io.tmpdir");

        try {
            MqttClient client = new MqttClient("tcp://10.0.0.137:1883", "12345", null);

            MqttConnectOptions conOpt = new MqttConnectOptions();
            conOpt.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            conOpt.setCleanSession(true);



            client.setCallback(this);
            client.connect(conOpt);

            client.subscribe("12345");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        Log.d(TAG, "connectionLost" + Log.getStackTraceString(cause));
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.d(TAG, "messageArrived" + topic + "" + message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.d(TAG, "deliveryComplete" + token.toString());
    }
}
