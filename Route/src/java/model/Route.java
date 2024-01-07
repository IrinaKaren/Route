package model;

import dbaccess.PGSQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Route{
    private int id;
    private String nom;
    private double coutReparation;

    // constructor
    public Route() {
    }

    public Route(int id, String nom, double coutReparation) {
        this.id = id;
        this.nom = nom;
        this.coutReparation = coutReparation;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCoutReparation() {
        return coutReparation;
    }

    public void setCoutReparation(double coutReparation) {
        this.coutReparation = coutReparation;
    }

    // toString method to display object information
    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", coutReparation=" + coutReparation +
                '}';
    }
    
    // Fonction
    public static List<Route> getAll() throws Exception{
        List<Route> listss = new ArrayList<>();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "select id, nom, cout_reparation from route";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Route ss = new Route();
            ss.setId(resultSet.getInt("id"));
            ss.setNom(resultSet.getString("nom"));
            ss.setCoutReparation(resultSet.getDouble("cout_reparation"));
            listss.add(ss);
        }
        connection.close();
        return listss;
    }
    
    public static List<Route> getById(int id_route) throws Exception {
        List<Route> listss = new ArrayList<>();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "SELECT id, nom, cout_reparation FROM route WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_route);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Route ss = new Route();
            ss.setId(resultSet.getInt("id"));
            ss.setNom(resultSet.getString("nom"));
            ss.setCoutReparation(resultSet.getDouble("cout_reparation"));
            listss.add(ss);
        }
        try (connection; preparedStatement; resultSet) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listss;
    }

}

