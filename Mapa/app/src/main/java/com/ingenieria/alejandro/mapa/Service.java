package com.ingenieria.alejandro.mapa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Alejandro on 12/04/2017.
 *
 * Vamos a utilizar Service para gestionar conexion y las zonas
 *
 */

public class Service {

    private static final String rutaServidor="http://www.encicla.gov.co/status/";

    private static Service instance = null;

    //Constructor Singleton
    protected Service(){
    }



    public static Service getInstance() {
        if(instance == null) {
            instance = new Service();
        }
        return instance;
    }

    //El metodo fija el json del webService
    public JSONObject get() throws IOException, JSONException {
        //Url del servidor
        URL u = new URL(rutaServidor);
        //Actualmente se utiliza HttpURLConnection porque HttpRequest,HttpClient estan depracated
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        //Fijamos tipo de metodo
        con.setRequestMethod("GET");

        con.setDoInput(true);
        //Abrimos la conexion
        con.connect();
        //obtenemos la informacion del servicio
        InputStream in = con.getInputStream();

        JSONObject result=streamToString(in);

        //Terminamos conexion
        con.disconnect();

        return result;

    }

    //Convertir InputSream a String, son se trunca al imprimirlo
    //Intente utilizando Gson pero no me funciono :c
    public JSONObject streamToString(InputStream in) throws IOException, JSONException {
        InputStreamReader reader = new InputStreamReader(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int ch;
        while((ch = reader.read())!= -1){
            out.write(ch);
        }

        String rta = new String(out.toByteArray());

        return new JSONObject(rta);
    }




    //Guarda la zona en la lista



    //El metodo trae el json del web service y lo retorna
    //Posdata: No me ha funcionado es utilizando Gson
    public void traerJsonService(Context context) throws IOException {

        ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            URL url = new URL(rutaServidor);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Reader reader=new BufferedReader(new InputStreamReader(in));
                /*  JsonObject jsonObject;
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement =jsonParser.parse(
                        new InputStreamReader(in, "UTF-8"));
                jsonObject = jsonElement.getAsJsonObject();
                System.out.println(""+jsonObject);
                */
                //Gson gson = new Gson();
            } finally {
                urlConnection.disconnect();
            }
        }else {
        }
    }
}
