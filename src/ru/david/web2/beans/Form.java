package ru.david.web2.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean
@RequestScoped
public class Form {
    private Float x;
    private Float y;
    private Float r;

    @ManagedProperty("#{results}")
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = (float)Math.round(x*1000)/1000;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = (float)Math.round(y*1000)/1000;
    }

    public Float getR() {
        return r;
    }

    public void setR(Float r) {
        this.r = (float)Math.round(r*1000)/1000;
    }

    public void process() throws SQLException, IOException {
        if (x == null)
            throw new IllegalStateException("X is null");
        if (y == null)
            throw new IllegalStateException("Y is null");
        if (r == null)
            throw new IllegalStateException("R is null");
        Result result = new Result();
        result.setX(x);
        result.setY(y);
        result.setR(r);
        result.setHit(isThisShitWorking(x, y, r));
        result.setTimestamp(System.currentTimeMillis());
        results.addResult(result);
        FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
    }

    private boolean isThisShitWorking(double x, double y, double r) {
        return  (x <= 0 && y <= 0 && x >= -r && y >= -r) ||
                (x >= 0 && y <= 0 && x*x + y*y <= r*r) ||
                (x >= 0 && y >= 0 && y < -x + r);
    }
}
