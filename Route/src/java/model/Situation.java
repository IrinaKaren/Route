package model;

import dbaccess.PGSQLConnection;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Situation {
    private int id_prioriter;
    private int id_route;
    private String route;
    private double cout_reparation;
    private int idpk;
    private String pk;
    private int id_degat;
    private String degat;
    private int id_situation;
    private Timestamp date_situation;
    
    //CONSTRUCTOR
    public Situation() {}
    
    // GETTER SETTER
    public int getId_prioriter() {
        return id_prioriter;
    }

    public void setId_prioriter(int id_prioriter) {
        this.id_prioriter = id_prioriter;
    }
    
    public int getId_route() {
        return id_route;
    }

    public void setId_route(int id_route) {
        this.id_route = id_route;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public double getCout_reparation() {
        return cout_reparation;
    }

    public void setCout_reparation(double cout_reparation) {
        this.cout_reparation = cout_reparation;
    }

    public int getIdpk() {
        return idpk;
    }

    public void setIdpk(int idpk) {
        this.idpk = idpk;
    }

    public int getId_degat() {
        return id_degat;
    }

    public void setId_degat(int id_degat) {
        this.id_degat = id_degat;
    }

    public String getDegat() {
        return degat;
    }

    public void setDegat(String degat) {
        this.degat = degat;
    }

    public int getId_situation() {
        return id_situation;
    }

    public void setId_situation(int id_situation) {
        this.id_situation = id_situation;
    }
    
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
    
    public Timestamp getDate_situation() {
        return date_situation;
    }

    public void setDate_situation(Timestamp date_situation) {
        this.date_situation = date_situation;
    }
    
    //------------------- BORNE KILOMETRIQUE -----------------------------------
    public static List<Situation> getPk(int id_route) throws Exception {
        List<Situation> listss = new ArrayList<>();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "select id,nom,id_route from borne_kilometrique WHERE id_route = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_route);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Situation ss = new Situation();
            ss.setIdpk(resultSet.getInt("id"));
            ss.setPk(resultSet.getString("nom"));
            ss.setId_route(resultSet.getInt("id_route"));
            listss.add(ss);
        }
        try (connection; preparedStatement; resultSet) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listss;
    }
    
    //------------------- DEGATS -----------------------------------------------
    public static List<Situation> getAllDegat() throws Exception {
        List<Situation> listss = new ArrayList<>();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "select id, nom from degat";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Situation ss = new Situation();
            ss.setId_degat(resultSet.getInt("id"));
            ss.setDegat(resultSet.getString("nom"));
            listss.add(ss);
        }
        connection.close();
        return listss;
    }
    
    
    //---------------------- PRIORITE -------------------------------
    public static List<Situation> getPrioriter(String type) throws Exception {
        List<Situation> dents = new ArrayList<>();

        try (Connection connection = PGSQLConnection.getConnection()) {
            String sql = "select id_prioriter, id_route, route, cout_reparation, idpk, pk, id_degat, degat, id_situation, date_situation " +
                        "from v_prioriter_esthetique";
            if(type.equalsIgnoreCase("economique")){
                sql = "select id_prioriter, id_route, route, cout_reparation, idpk, pk, id_degat, degat, id_situation, date_situation " +
                        "from v_prioriter_economique";
            }
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Situation dent = new Situation();
                    dent.setId_prioriter(resultSet.getInt("id_prioriter"));
                    dent.setId_route(resultSet.getInt("id_route"));
                    dent.setRoute(resultSet.getString("route"));
                    dent.setCout_reparation(resultSet.getDouble("cout_reparation"));
                    dent.setIdpk(resultSet.getInt("idpk"));
                    dent.setPk(resultSet.getString("pk"));
                    dent.setId_degat(resultSet.getInt("id_degat"));
                    dent.setDegat(resultSet.getString("degat"));
                    dent.setId_situation(resultSet.getInt("id_situation"));
                    dent.setDate_situation(resultSet.getTimestamp("date_situation"));
                    dents.add(dent);
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dents;
    }
    
    
    //---------------- CONSTRUCTION --------------------------------------------
    public void construction() throws Exception{
        Connection connection = PGSQLConnection.getConnection();
        String sql = "INSERT INTO construction (date_situation, id_route, id_pk, id_degat, cout) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, this.getDate_situation());
            preparedStatement.setInt(2, this.getId_route());
            preparedStatement.setInt(3, this.getIdpk());
            preparedStatement.setInt(4, this.getId_degat());
            preparedStatement.setDouble(5, this.getCout_reparation());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Timestamp getDateMax() throws Exception{
        Timestamp date_max = null;
        Connection connection = PGSQLConnection.getConnection();
        String sql = "select max(date_situation) as date_situation from construction";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            date_max = resultSet.getTimestamp("date_situation");
        }
        connection.close();
        return date_max;
    }
    
    //------------------ SITUATION ROUTIERE ------------------------------------
    public void update() throws Exception {
        Connection connection = PGSQLConnection.getConnection();
        String sql = "UPDATE situation_routiere " +
                     "SET date_situation = ?, id_degat = ? " +
                     "WHERE id_pk = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, this.getDate_situation());
            preparedStatement.setInt(2, this.getId_degat());
            preparedStatement.setInt(3, this.getIdpk());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Situation> etablissementConstruction(String type,double argent)throws Exception{
        List<Situation> routes = new ArrayList();
        List<Situation> listprioriter = getPrioriter(type);
        Timestamp date_now = Timestamp.valueOf(LocalDateTime.now());
        double cout_total = 0;
        for(int i = 0; i<listprioriter.size(); i++){
            int id_degat = listprioriter.get(i).getId_degat();
            int id_pk = listprioriter.get(i).getIdpk();
            double cout = listprioriter.get(i).getCout_reparation()*(10-id_degat);
            double reste_argent = argent - cout_total;
            if(reste_argent < cout || id_degat == 10)continue;
            Situation situation = new Situation();
            situation.setDate_situation(date_now);
            situation.setId_degat(10);
            situation.setIdpk(id_pk);
            situation.update();
            
            // construction mitazona historique
            situation.setDate_situation(date_now);
            situation.setId_route(listprioriter.get(i).getId_route());
            situation.setIdpk(id_pk);
            situation.setId_degat(id_degat);
            situation.setCout_reparation(cout);
            situation.construction();
            cout_total += cout;
        }
        return routes;
    }
       
}
