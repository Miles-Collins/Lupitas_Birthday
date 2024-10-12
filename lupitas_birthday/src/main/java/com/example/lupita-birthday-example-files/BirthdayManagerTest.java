
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BirthdayManagerTest {

    private BirthdayManager birthdayManager;

    @BeforeEach
    public void setUp() {
        birthdayManager = new BirthdayManager();
        JSONArray jsonData = new JSONArray();
        JSONObject person1 = new JSONObject();
        person1.put("name", "Alice");
        person1.put("birthday", "01/01/1990");
        jsonData.add(person1);
        JSONObject person2 = new JSONObject();
        person2.put("name", "Bob");
        person2.put("birthday", "02/02/1991");
        jsonData.add(person2);
        birthdayManager.initializeMap(jsonData);
    }

    @Test
    public void testGetPeople() {
        ArrayList<String> result = birthdayManager.getPeople("Alice");
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0));
    }

    @Test
    public void testGetBirthday() {
        String birthday = birthdayManager.getBirthday("Alice");
        assertEquals("01/01/1990", birthday);
    }

    @Test
    public void testGetPeopleNotFound() {
        ArrayList<String> result = birthdayManager.getPeople("Charlie");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetBirthdayNotFound() {
        String birthday = birthdayManager.getBirthday("Charlie");
        assertNull(birthday);
    }

    @Test
    public void testGetPeopleCaseInsensitive() {
        ArrayList<String> result = birthdayManager.getPeople("alice");
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0));
    }

    @Test
    public void testGetBirthdayCaseInsensitive() {
        String birthday = birthdayManager.getBirthday("alice");
        assertEquals("01/01/1990", birthday);
    }

    @Test
    public void testGetPeoplePartialName() {
        ArrayList<String> result = birthdayManager.getPeople("Ali");
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0));
    }

    @Test
    public void testGetBirthdayPartialName() {
        String birthday = birthdayManager.getBirthday("Ali");
        assertEquals("01/01/1990", birthday);
    }

    @Test
    public void testInitializeMapWithEmptyData() {
        BirthdayManager emptyBirthdayManager = new BirthdayManager();
        JSONArray emptyJsonData = new JSONArray();
        emptyBirthdayManager.initializeMap(emptyJsonData);
        ArrayList<String> result = emptyBirthdayManager.getPeople("Alice");
        assertTrue(result.isEmpty());
    }
}
