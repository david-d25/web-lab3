package ru.david.web2.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Form {
    private Float x;
    private Float y;
    private Float r;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getR() {
        return r;
    }

    public void setR(Float r) {
        this.r = r;
    }
}
