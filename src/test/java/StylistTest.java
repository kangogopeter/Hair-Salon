import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", "peter", "1234");
    }

    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            String deleteClientsQuery = "DELETE FROM clients *;";
            con.createQuery(deleteStylistsQuery).executeUpdate();
            con.createQuery(deleteClientsQuery).executeUpdate();
        }
    }


    @Test
    public void save_savesClientIdIntoDB_true() {
        Client myClient = new Client("Household chores");
        myClient.save();
        Stylist myStylist = new Stylist("Mow the lawn", myClient.getId());
        myStylist.save();
        Stylist savedStylist = Stylist.find(myStylist.getId());
        assertEquals(savedStylist.getClientId(), myClient.getId());
    }

    @Test
    public void Stylist_instantiatesCorrectly_true() {
        Stylist myStylist = new Stylist("Mow the lawn", 1);
        assertEquals(true, myStylist instanceof Stylist);
    }


    @Test
    public void save_returnsTrueIfDescriptionsAre_the_Same() {
        Stylist myStylist = new Stylist("Mow the lawn", 1);
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
    }


    @Test
    public void Stylist_instantiatesCorrectly_String() {
        Stylist myStylist = new Stylist("Mow the lawn", 1);
        assertEquals("Mow the lawn", myStylist.getDescription());
    }


    @Test
    public void equals_returnsTrueIfDescriptionsAre_the_Same() {
        Stylist firstStylist = new Stylist("Mow the lawn", 1);
        Stylist secondStylist = new Stylist("Mow the lawn", 1);
        assertTrue(firstStylist.equals(secondStylist));
    }


    @Test
    public void all_returnsAllInstancesofStylist_true() {
        Stylist firstStylist = new Stylist("Mow the lawn", 1);
        firstStylist.save();
        Stylist secondStylist = new Stylist("Buy groceries", 1);
        secondStylist.save();
        assertEquals(true, Stylist.all().contains(firstStylist));
        assertEquals(true, Stylist.all().contains(secondStylist));
    }


    @Test
    public void save_assignIdToObject() {
        Stylist myStylist = new Stylist("Mow the lawn", 1);
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
    }

    @Test
    public void getId_stylistsInstantiatesWithAnID() {
        Stylist myStylist = new Stylist("Mow the lawn", 1);
        myStylist.save();
        assertTrue(myStylist.getId() > 0);
    }

    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
        Stylist firstStylist = new Stylist("Mow the lawn", 1);
        firstStylist.save();
        Stylist secondStylist = new Stylist("Buy groceries", 1);
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }


//the updates part.....

    @Test
    public void update_updatesStylistDescription_true() {
        Stylist myStylist = new Stylist("Mow the lawn", 1);
        myStylist.save();
        myStylist.update("Take a nap");
        assertEquals("Take a nap", Stylist.find(myStylist.getId()).getDescription());
    }


    //Deleting...
    @Test
    public void delete_deletesStylist_true() {
        Stylist myStylist = new Stylist("Mow the lawn", 1);
        myStylist.save();
        int myStylistId = myStylist.getId();
        myStylist.delete();
        assertEquals(null, Stylist.find(myStylistId));
    }
}