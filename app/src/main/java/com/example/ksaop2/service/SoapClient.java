package com.example.ksaop2.service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.util.Vector;

public class SoapClient {

    private static final String NAMESPACE = "http://services.webservice.example.com/";
    private static final String URL = "http://10.10.3.93:8080/services/ws";

    public interface SoapResponse<T> {
        void onSuccess(T response);
        void onError(Exception exception);
    }

    public void getCompteById(long id, SoapResponse<SoapObject> response) {
        new Thread(() -> {
            try {
                SoapObject request = new SoapObject(NAMESPACE, "getCompteById");
                request.addProperty("id", id);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call("", envelope);
                response.onSuccess((SoapObject) envelope.getResponse());
            } catch (Exception e) {
                response.onError(e);
            }
        }).start();
    }

    public void getAllComptes(SoapResponse<Vector<SoapObject>> response) {
        new Thread(() -> {
            try {
                SoapObject request = new SoapObject(NAMESPACE, "getComptes");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call("", envelope);
                response.onSuccess((Vector<SoapObject>) envelope.getResponse());
            } catch (Exception e) {
                response.onError(e);
            }
        }).start();
    }

    public void createCompte(String solde, String type, SoapResponse<SoapObject> response) {
        new Thread(() -> {
            try {
                SoapObject request = new SoapObject(NAMESPACE, "createCompte");
                request.addProperty("solde", solde);
                request.addProperty("type", type);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call("", envelope);
                response.onSuccess((SoapObject) envelope.getResponse());
            } catch (Exception e) {
                response.onError(e);
            }
        }).start();
    }

    public void deleteCompte(long id, SoapResponse<Boolean> response) {
        new Thread(() -> {
            try {
                SoapObject request = new SoapObject(NAMESPACE, "deleteCompte");
                request.addProperty("id", id);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call("", envelope);
                response.onSuccess(Boolean.parseBoolean(envelope.getResponse().toString()));
            } catch (Exception e) {
                response.onError(e);
            }
        }).start();
    }
}
