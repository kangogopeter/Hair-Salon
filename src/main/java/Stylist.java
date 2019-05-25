import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Stylist{
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;
    private int clientId;

    public Stylist(String description, int clientId){
        this.description = description;
        completed = false;
        createdAt = LocalDateTime.now();
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }
    public boolean isCompleted(){
        return completed;
    }
    public LocalDateTime getCreatedAt(){
        return  createdAt;
    }

    public int getId() {
        return id;
    }

    public int getClientId(){
        return clientId;
    }

    public static Stylist find(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    public static List<Stylist> all() {
        String sql = "SELECT id, description, clientId FROM stylists";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists(description, clientId) VALUES (:description,:clientId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("description", this.description)
                    .addParameter("clientId", this.clientId)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public boolean equals(Object otherStylist){
        if (!(otherStylist instanceof Stylist)){
            return false;
        }else {
            Stylist newStylist = (Stylist) otherStylist;
            return this.getDescription().equals(newStylist.getDescription()) &&
                    this.getId() == newStylist.getId() &&
                    this.clientId == newStylist.getClientId();
        }
    }


    //for updates...
    public void update(String description){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE stylists SET description = :description WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //for deleting...

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM stylists WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
