package ru.david.web2.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class Results {
    public List<Result> getResults() {
        return new ArrayList<>();
    }
}
