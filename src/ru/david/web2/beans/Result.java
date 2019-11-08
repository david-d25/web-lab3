package ru.david.web2.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

@ManagedBean
@NoneScoped
public class Result {
    private double x, y, r;
    private boolean hit;
    private long timestamp;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String timeFriendly(long millis) {
        long delta = System.currentTimeMillis() - millis;
        if (delta < 1000)
            return "Только что";
        if (delta < 1000*60)
            return delta/1000 + " сек. назад";
        if (delta < 1000*60*60)
            return delta/1000/60 + " мин. назад";
        return delta/1000/60/60 + " часов назад";
    }
}
