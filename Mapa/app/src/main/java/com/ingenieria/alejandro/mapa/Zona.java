package com.ingenieria.alejandro.mapa;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alejandro on 12/04/2017.
 */

public class Zona {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("desc")
    public String desc;
    @SerializedName("items")
    public List<Casa> casas;

    public Zona(){
        casas=new ArrayList<>();
    }

    /* get and set*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Casa> getCasas() {
        return casas;
    }

    public void setCasas(List<Casa> casas) {
        this.casas = casas;
    }

    //Agrega casa a la zona
    public void agregarCasa(Casa casa){
        casas.add(casa);
    }

}
