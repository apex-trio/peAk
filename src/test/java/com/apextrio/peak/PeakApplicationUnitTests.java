package com.apextrio.peak;

import com.apextrio.peak.appuser.AppUser;
import com.apextrio.peak.resort.Resort;
import com.apextrio.peak.team.Team;
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


    @Test
    public void ResortGettersAndSetters() {
        Resort testResort = new Resort();
        testResort.setName("Mission Ridge");
        testResort.setNick("The Ridge");
        testResort.setLatitude((float) 46.9233);
        testResort.setLongitude((float) -121.476);
        testResort.setOtsUrl("https://fakeOtsUrl.com");
        testResort.setWebsite("https://skiResortWebsite.com");
        testResort.setWidgetId(1);

        assertTrue("full name of resort should be the same as what the getter gives us",
                testResort.getName().equals("Mission Ridge"));

        assertTrue("nickname of resort should be the same as what the getter gives us",
                testResort.getNick().equals("The Ridge"));

        assertEquals(testResort.getLatitude(), (float) 46.9233, 0);

        assertEquals(testResort.getLongitude(), (float) -121.476, 0);


        assertTrue("fake ots url should be the same as what the getter gives us",
                testResort.getOtsUrl().equals("https://fakeOtsUrl.com"));

        assertTrue("fake resort website should be the same as what the getter gives us",
                testResort.getWebsite().equals("https://skiResortWebsite.com"));

        assertEquals(testResort.getWidgetId(), 1, 0);
    }


    @Test
    public void TeamGettersAndSetters() {
        Team testTeam = new Team();
        testTeam.setName("Squares aren't for squares");
        testTeam.setDifficulty(1);
        testTeam.setDescription("Casual riding");
        testTeam.setCapacity(6);
        testTeam.setDateCreated("01/5/2019");
        testTeam.setDateGoing("01/19/2019");


        assertTrue("group name should be the same as what the getter gives us",
                testTeam.getName().equals("Squares aren't for squares"));

        assertEquals(testTeam.getDifficulty(), 1, 0);

        assertTrue("last name should be the same as what the getter gives us",
                testTeam.getDescription().equals("Casual riding"));

        assertEquals(testTeam.getCapacity(), 6, 0);

        assertTrue("date created should be the same as what the getter gives us",
                testTeam.getDateCreated().equals("01/5/2019"));

        assertTrue("date going should be the same as what the getter gives us",
                testTeam.getDateGoing().equals("01/19/2019"));
    }

}
