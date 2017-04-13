package com.ingenieria.alejandro.mapa;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by Alejandro on 13/04/2017.
 *
 * Lista zona hace referencia a las estaciones
 *
 * */

public class ListaZona {

    private ArrayList<Zona> zonasJson;

    private static ListaZona instance = null;

    protected ListaZona() {
            zonasJson=new ArrayList<>();
    }
//Singleton
    public static ListaZona getInstance() {
        if(instance == null) {
            instance = new ListaZona();
        }
        return instance;
    }

    //Agrega una nueva zona a la lista
    public void agregarNuevaZona(Zona zona){
        zonasJson.add(zona);
    }


    //Recorre el json para guardar las zonas
    public void fijarZonasObtenidas()  {

        Service service=Service.getInstance();
        JSONObject jsonObject= null;
        try {
            jsonObject = service.get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {

            JSONArray stations=jsonObject.getJSONArray("stations");
            Zona zona;
            Casa casa;
            for(int i=0;i<stations.length();i++)
            {
                zona=new Zona();
                JSONObject objZonaJson = stations.getJSONObject(i);
                zona.setId(objZonaJson.get("id").toString());
                zona.setName(objZonaJson.get("name").toString());
                zona.setDesc(objZonaJson.get("desc").toString());
                JSONArray items=objZonaJson.getJSONArray("items");
                for (int j=0;j<items.length();j++){

                    casa=new Casa();
                    JSONObject objCasaJson = items.getJSONObject(j);
                    casa.setId(Integer.parseInt(objCasaJson.get("id").toString()));
                    casa.setName(objCasaJson.get("name").toString());
                    casa.setAddress(objCasaJson.get("address").toString());
                    casa.setDescription(objCasaJson.get("description").toString());
                    casa.setLat(objCasaJson.get("lat").toString());
                    casa.setLon(objCasaJson.get("lon").toString());
                    casa.setCapacity(Integer.parseInt(objCasaJson.get("capacity").toString()));
                    casa.setBikes(Integer.parseInt(objCasaJson.get("bikes").toString()));
                    //casa.setPlaces((objCasaJson.get("places")==null)?null:Integer.parseInt(objCasaJson.get("places").toString()));
                    casa.setPicture(objCasaJson.get("picture").toString());
                    casa.setBikes_state(objCasaJson.get("bikes_state").toString());
                    casa.setPlaces_state(objCasaJson.get("places_state").toString());
                    casa.setClosed(Integer.parseInt(objCasaJson.get("closed").toString()));
                    casa.setCdo(Integer.parseInt(objCasaJson.get("cdo").toString()));

                    zona.agregarCasa(casa);
                }
                agregarNuevaZona(zona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//Devuelve las zonas
    public ArrayList<Zona> getZonasJson() {
        return zonasJson;
    }
}
