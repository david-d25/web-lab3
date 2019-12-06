package ru.david.web2.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class Results {
    private DataSource dataSource;

    private Connection connection;

    public Results() {
        initConnection();
    }

    private void initConnection() {

        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/PostgresDS");
        } catch (NamingException e) {
            throw new IllegalStateException("Could not create connection!!!", e);
        }

        try {
            connection = dataSource.getConnection();
            connection.createStatement().execute(
                    "create table if not exists results (" +
                            "x numeric(5, 3), y numeric(5, 3), r numeric(5, 3), hit boolean, timestamp timestamp)"
            );
        } catch (SQLException e) {
                        throw new IllegalStateException("Could not create connection!!!", e);
        }
    }

    public void addResult(Result result) throws SQLException {
        if (connection == null)
            initConnection();
        PreparedStatement s = connection.prepareStatement(
                "insert into results (x, y, r, hit, timestamp) values (?, ?, ?, ?, ?)"
        );
        s.setDouble(1, result.getX());
        s.setDouble(2, result.getY());
        s.setDouble(3, result.getR());
        s.setBoolean(4, result.isHit());
        s.setTimestamp(5, new Timestamp(result.getTimestamp()));
        s.execute();
    }

    public List<Result> getResults() throws SQLException {
        if (connection == null)
            initConnection();
        ResultSet rs = connection.createStatement().executeQuery("select * from results order by timestamp desc limit 100");
        List<Result> result = new ArrayList<>();
        while (rs.next()) {
            Result current = new Result();
            current.setX(rs.getDouble("x"));
            current.setY(rs.getDouble("y"));
            current.setR(rs.getDouble("r"));
            current.setHit(rs.getBoolean("hit"));
            current.setTimestamp(rs.getTimestamp("timestamp").getTime());
            result.add(current);
        }
        return result;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
