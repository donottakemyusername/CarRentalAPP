package ca.ubc.cs304.model;

import java.util.ArrayList;

public class VehicleTypeModel {
    private final String vtname;
    private final String features;
    private final float wrate;
    private final float drate;
    private final float hrate;
    private final float wirate;
    private final float dirate;
    private final float hirate;
    private final float krate;

    public VehicleTypeModel(String vtname, String features, float wrate, float drate, float hrate,
                            float wirate, float dirate, float hirate, float krate) {
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

    public float getWrate() {
        return wrate;
    }

    public float getDrate() {
        return drate;
    }

    public float getHrate() {
        return hrate;
    }

    public float getWirate() {
        return wirate;
    }

    public float getDirate() {
        return dirate;
    }

    public float getHirate() {
        return hirate;
    }

    public float getKrate() {
        return krate;
    }
}