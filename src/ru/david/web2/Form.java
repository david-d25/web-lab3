package ru.david.web2;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Form {
    private Float x = null;
    private float y;
    private float r = 1;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
