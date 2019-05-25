import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Client {
    private String name;
    private int id;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Client> all() {
        String sql = "SELECT id, name FROM clients";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public int getId() {
        return id;
    }

    public List<Stylist> getStylists() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists WHERE clientId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Stylist.class);
        }
    }

    public static Client find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
            Client client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return client;
        }
    }

    public void save () {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (name) Values (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public boolean equals (Object otherClient){
        if (!(otherClient instanceof Client)) {
            return false;
        } else {
            Client newClient = (Client) otherClient;
            return this.getName().equals(newClient.getName()) &&
                    this.getId() == newClient.getId();
        }
    }
}
