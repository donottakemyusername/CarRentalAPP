package ca.ubc.cs304.model;

import java.util.ArrayList;

public class VehicleTypeModel {
    private String vtname;
    private String features;
    private Double  wrate;
    private Double drate;
    private Double hrate;
    private Double wirate;
    private Double dirate;
    private Double hirate;
    private Double krate;

    public VehicleTypeModel(String vtname, String features, Double wrate, Double drate, Double hrate,
                            Double  wirate, Double dirate, Double hirate, Double krate) {
        this.vtname = vtname;
        this.features = features;
        this.wrate = wrate;
        this.drate = drate;
        this.hrate = hrate;
        this.wirate = wirate;
        this.dirate = dirate;
        this.hirate = hirate;
        this.krate = krate;
    }

    public String getVtname() {
        return vtname;
    }

    public String getFeatures() {
        return features;
    }

    public Double getWrate() {
        return wrate;
    }

    public Double getDrate() {
        return drate;
    }

    public Double getHrate() {
        return hrate;
    }

    public Double getWirate() {
        return wirate;
    }

    public Double getDirate() {
        return dirate;
    }

    public Double getHirate() {
        return hirate;
    }

    public Double getKrate() {
        return krate;
    }
}