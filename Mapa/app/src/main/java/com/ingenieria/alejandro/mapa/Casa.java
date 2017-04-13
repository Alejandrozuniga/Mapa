package com.ingenieria.alejandro.mapa;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alejandro on 12/04/2017.
 *
 * Casa lo he tomado como los items del json
 *
 */

class Casa {
    @SerializedName("id")
    public int id;
    @SerializedName("order")
    public int order;
    @SerializedName("capacity")
    public int capacity;
    @SerializedName("bikes")
    public int bikes;
    @SerializedName("places")
    public Integer places;
    @SerializedName("closed")
    public int closed;
    @SerializedName("cdo")
    public int cdo;

    @SerializedName("name")
    public String name;
    @SerializedName("address")
    public String  address;
    @SerializedName("description")
    public String description;
    @SerializedName("lat")
    public String lat;
    @SerializedName("lon")
    public String lon;
    @SerializedName("type")
    public String type;
    @SerializedName("picture")
    public String picture;
    @SerializedName("bikes_state")
    public String bikes_state;
    @SerializedName("places_state")
    public String places_state;

    public Casa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBikes() {
        return bikes;
    }

    public void setBikes(int bikes) {
        this.bikes = bikes;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getCdo() {
        return cdo;
    }

    public void setCdo(int cdo) {
        this.cdo = cdo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBikes_state() {
        return bikes_state;
    }

    public void setBikes_state(String bikes_state) {
        this.bikes_state = bikes_state;
    }

    public String getPlaces_state() {
        return places_state;
    }

    public void setPlaces_state(String places_state) {
        this.places_state = places_state;
    }
}
