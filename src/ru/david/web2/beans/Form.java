package ru.david.web2.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class Form {
    @NotNull(message = "X пуст")
    private Float x;

    @NotNull(message = "Y пуст")
    @Min(value=-3, message = "Y должен быть больше, чем -3")
    @Max(value = 5, message = "Y должен быть меньше, чем 5")
    private Float y;

    @NotNull(message = "R пуст")
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
