import com.apextrio.peak.AppUser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PeakApplicationUnitTests {

    @Test
    public void AppUserGettersAndSetters() {
        AppUser testUser = new AppUser();
        testUser.setUsername("snowboarder999");
        testUser.setPassword("123456");
        testUser.setBio("surfing nar all day long");
        testUser.setFirstName("Joshua");
        testUser.setLastName("Dixon");

        assertTrue("username should be the same as what the getter gives us",
                testUser.getUsername().equals("snowboarder999"));

        assertTrue("password should be the same as what the getter gives us",
                testUser.getPassword().equals("123456"));

        assertTrue("bio should be the same as what the getter gives us",
                testUser.getBio().equals("surfing nar all day long"));

        assertTrue("first name should be the same as what the getter gives us",
                testUser.getFirstName().equals("Joshua"));

        assertTrue("last name should be the same as what the getter gives us",
                testUser.getLastName().equals("Dixon"));

    }

}
