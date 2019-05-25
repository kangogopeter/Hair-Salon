import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ClientTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", "peter", "1234");
    }

    @Test
    public void save_assignsIdToObject() {
        Client myClient = new Client("Household chores");
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
    }

    @Test
    public void getId_clientsInstantiateWithAnId_1() {
        Client testClient = new Client("Home");
        testClient.save();
        assertTrue(testClient.getId() > 0);
    }
    //
    @Test
    public void save_savesIntoDatabase_true() {
        Client myClient = new Client("Household chores");
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }

    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
        Client firstClient = new Client("Household chores");
        Client secondClient = new Client("Household chores");
        assertTrue(firstClient.equals(secondClient));
    }

    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String deleteTasksQuery = "DELETE FROM stylists *;";
            String deleteClientsQuery = "DELETE FROM clients *;";
            con.createQuery(deleteTasksQuery).executeUpdate();
            con.createQuery(deleteClientsQuery).executeUpdate();
        }
    }
    @Test
    public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("Home");
        firstClient.save();
        Client secondClient = new Client("Work");
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }

    @Test
    public void find_returnsClientWithSameId_secondClient() {
        Client firstClient = new Client("Home");
        firstClient.save();
        Client secondClient = new Client("Work");
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }

    @Test
    public void getStylist_retrievesALlStylistsFromDatabase_stylistsList(){
        Client myClient = new Client(("Household chores"));
        myClient.save();
        Stylist firstStylist = new Stylist("Mow the lawn",  myClient.getId());
        firstStylist.save();
        Stylist secondStylist = new Stylist("Do the dishes",  myClient.getId());
        secondStylist.save();
        Stylist[] stylists = new Stylist[][] {firstStylist,secondStylist};
        assertTrue(myClient.getStylists().containsAll(Arrays.asList(stylists)));
    }

}