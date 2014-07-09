package com.jeffthefate.utils.json.geocoding;

import java.util.List;

public class Geocode {

    private List<AddressComponent> address_components;
    private String formatted_address;
    private Geometry geometry;
    private List<String> types;

    public List<AddressComponent> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "address_components: " + getAddress_components() +
                ", formatted_address: " + getFormatted_address() +
                ", geometry: " + getGeometry() + ", types: " + getTypes();
    }

}
