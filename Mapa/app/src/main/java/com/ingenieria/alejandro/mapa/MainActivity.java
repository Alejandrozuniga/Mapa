package com.ingenieria.alejandro.mapa;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapa;
    private Marker marker;
    private ListaZona lz;
    private GoogleMap objGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapa = (MapView) findViewById(R.id.map);

        if (mapa != null) {
            mapa.onCreate(null);
            mapa.onResume();
            mapa.getMapAsync(this);
        }

        //obtencion de instancia
        lz=ListaZona.getInstance();
        //ejecucion de la tarea asincrona
        new SincronizarJson().execute();


    }
//
    public void imprimirJson() throws IOException, JSONException {
        lz.fijarZonasObtenidas();
           }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        objGoogleMap=googleMap;
        MapsInitializer.initialize(this);
        objGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //Ubicacion propia
            objGoogleMap.setMyLocationEnabled(true);
            //Acercamieto al departamento de antioquia
            LatLng objAntioquia = new LatLng(6.29004621, -75.56447983);
            CameraPosition myLocation2 = CameraPosition.builder().target(objAntioquia).zoom(11).bearing(0).tilt(45).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(myLocation2));


        }

}

//Agregamos los punteros al mapa
    //Utilizamos como base de infomacion la lz(Lista de zonas) que tiene la informacion del json
    public void agregarPuntero() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            int totalZonas=lz.getZonasJson().size();
            float[] hue=new float[]{BitmapDescriptorFactory.HUE_ORANGE, BitmapDescriptorFactory.HUE_GREEN,
                    BitmapDescriptorFactory.HUE_CYAN,BitmapDescriptorFactory.HUE_BLUE,BitmapDescriptorFactory.HUE_YELLOW,
                    BitmapDescriptorFactory.HUE_AZURE,BitmapDescriptorFactory.HUE_MAGENTA
            };
            for(int i=0;i<totalZonas;i++){
                int casas=lz.getZonasJson().get(i).getCasas().size();
                for (int j=0;j<casas;j++){
                    LatLng latLng= new LatLng(Double.parseDouble(lz.getZonasJson().get(i).getCasas().get(j).getLat()),Double.parseDouble(lz.getZonasJson().get(i).getCasas().get(j).getLon()));
                    objGoogleMap.addMarker(new MarkerOptions().position(latLng).title(lz.getZonasJson().get(i).getCasas().get(j).getName()).snippet(lz.getZonasJson().get(i).getName())).setIcon(BitmapDescriptorFactory.defaultMarker(hue[i]));
                }
            }
        }
    }

/*
Metodo que traza lineas
encierra el departamento de antioquia
 */
    public void trazarLinea() {
                LatLng punto1=new LatLng(6.35146864, -75.68515778);
                LatLng punto2=new LatLng(6.35897533,-75.44757843);
                LatLng punto3=new LatLng(6.1466991, -75.45581818);
                LatLng punto4=new LatLng(6.1432856, -75.73802948);
                LatLng punto5= new LatLng(6.35146864, -75.68515778);
        PolylineOptions lineas = new PolylineOptions()
                .add(punto1)
                .add(punto2)
                .add(punto3)
                .add(punto4)
                .add(punto5);

        lineas.width(8);
        lineas.color(Color.BLACK);

        objGoogleMap.addPolyline(lineas);
    }
/**
 * Tarea asyncona que fija la informacion y agrega los markers al mapa
 */

    class SincronizarJson extends AsyncTask<String, String, String>{
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String s) {
        agregarPuntero();
        trazarLinea();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            try {
                imprimirJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {

        }

        return "";
    }
}

}