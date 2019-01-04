package com.apextrio.peak;

import com.apextrio.peak.appuser.AppUser;
import com.apextrio.peak.appuser.AppUserRepository;
import com.apextrio.peak.resort.Resort;
import com.apextrio.peak.resort.ResortRepository;
import com.apextrio.peak.team.Team;
import com.apextrio.peak.team.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//------------------------------------------------------------------------------------------------------------------------------
//TO PROPERLY RUN THESE TESTS, ENABLE AUTO CREATE IN application.properties AND THEN RUN THE PROVIDED SQL FILES ON YOUR DATABASE
//------------------------------------------------------------------------------------------------------------------------------
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class PeakApplicationTests {

	@Autowired
	private AppUserRepository userRepo;

	@Autowired
	private TeamRepository teamRepo;

	@Autowired
	private ResortRepository resortRepo;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}


	@Test
	public void contextLoads() {
	}

	//---------------------------------------
	//TESTING ROUTES AVAILABLE ON THE WEBSITE
	//---------------------------------------
	@Test
	public void testHome() {
		try {
			this.mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}

	}

	@Test
	@WithUserDetails("NJCrain")
	public void testTeamsId() {
		try {
			MvcResult response = this.mvc.perform(get("/teams/6")).andExpect(status().isOk()).andExpect(view().name("team")).andExpect(model().attribute("inGroup", false)).andReturn();
			assertTrue(response.getResponse().getContentAsString().contains("<div id=\"users\">"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	@WithUserDetails("NJCrain")
	public void testTeamsIdInGroup() {
		try {
			this.mvc.perform(get("/teams/1")).andExpect(status().isOk()).andExpect(view().name("team")).andExpect(model().attribute("inGroup", true));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testTeamsIdNoAuth() {
		try {
			MvcResult response = this.mvc.perform(get("/teams/3")).andExpect(status().isOk()).andExpect(view().name("team")).andExpect(model().attribute("inGroup", null)).andReturn();
			assertFalse(response.getResponse().getContentAsString().contains("<div id=\"users\">"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testTeamsIdInvalidId() {
		try {
			this.mvc.perform(get("/teams/10000")).andExpect(status().isNotFound()).andExpect(view().name("error"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testLogin() {
		try {
			this.mvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testSignup() {
		try {
			this.mvc.perform(get("/signup")).andExpect(status().isOk()).andExpect(view().name("sign_up"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	@WithUserDetails("NJCrain")
	public void testNewTeam() {
		try {
			this.mvc.perform(get("/newteam?resortId=2")).andExpect(status().isOk()).andExpect(view().name("teamForm"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testNewTeamNoAuth() {
		try {
			this.mvc.perform(get("/newteam?resortId=2")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	@WithUserDetails("NJCrain")
	public void testMyProfile() {
		try {
			this.mvc.perform(get("/myProfile")).andExpect(status().isOk()).andExpect(view().name("my_profile"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testMyProfileNoAuth() {
		try {
			this.mvc.perform(get("/myProfile")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	//---------------------------------
	//TESTING FOR APPLICATION ENDPOINTS
	//---------------------------------

	@Test
	@WithUserDetails("NJCrain")
	public void testPostResortsId() {
		try {
			this.mvc.perform(post("/resorts/2?name=A Name&description=test&difficulty=3&capacity=5&dateGoing=2019-02-10T08:00")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrlPattern("/teams/*"));
		} catch (java.lang.Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testPostResortsIdNoAuth() {
		try {
			this.mvc.perform(post("/resorts/2?name=A Name&description=test&difficulty=3&capacity=5&dateGoing=2019-02-10T08:00")).andExpect(status().isForbidden());
		} catch (java.lang.Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testGetResortId() {
		try {
			this.mvc.perform(get("/resorts/4")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk()).andDo(print());
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	@WithUserDetails("NJCrain")
	public void testPostTeamsId() {
		try {
			this.mvc.perform(post("/teams/3")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/teams/3"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testPostTeamsIdNoAuth() {
		try {
			this.mvc.perform(post("/teams/3")).andExpect(status().isForbidden()).andExpect(redirectedUrl("/error"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	@WithUserDetails("NJCrain")
	public void	testDeleteTeamId() {
		try {
			this.mvc.perform(delete("/teams/2")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/teams/2"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	@WithUserDetails("jasonb315")
	public void	testDeleteTeamIdLastUser() {
		try {
			this.mvc.perform(delete("/teams/2")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testPostSignup() {
		try {
			this.mvc.perform(post("/signup?username=test&password=123456&firstName=test&lastName=test&bio=test")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
		} catch (java.lang.Exception e ) {
			System.err.println(e);
		}
	}

	@Test
	public void testPostSignupWithTeamId() {
		try {
			this.mvc.perform(post("/signup?teamId=1&username=test&password=123456&firstName=test&lastName=test&bio=test")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/teams/1"));
		} catch (java.lang.Exception e ) {
			System.err.println(e);
		}
	}

	@Test
	@WithUserDetails("NJCrain")
	public void testPostUpdateProfile() {
		try {
			this.mvc.perform(post("/updateProfile?bioUpdate=test")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/myProfile"));
		} catch (java.lang.Exception e) {
			System.err.println(e);
		}
	}

	//------------
	//CRUD TESTING
	//------------

	@Test
	public void testCreateUser() {
		AppUser testUser = new AppUser();
		testUser = userRepo.save(testUser);
		assertNotNull(testUser);
		assertTrue(testUser.getId() != 0);
	}

	@Test
	public void testCreateTeam() {
		Team testTeam = new Team();
		testTeam = teamRepo.save(testTeam);
		assertNotNull(testTeam);
		assertTrue(testTeam.getId() != 0);
	}

	@Test
	public void testReadUser() {
		AppUser user = userRepo.findByUsername("NJCrain");
		assertNotNull(user);
		assertEquals("test", user.getBio());
		assertEquals("Nick", user.getFirstName());
		assertEquals("Crain", user.getLastName());
	}

	@Test
	public void testReadTeam() {
		Team testTeam = teamRepo.findById( (long) 3).get();
		assertEquals("Team 2-2", testTeam.getName());
		assertEquals(3, testTeam.getDifficulty());
		assertEquals("Descriptions are lame", testTeam.getDescription());
		assertEquals(3, testTeam.getCapacity());
	}

	@Test
	public void testReadResort() {
		Resort r = resortRepo.findById((long) 1).get();
		assertNotNull(r);
		assertEquals("Crystal", r.getNick());
		assertEquals(124, r.getWidgetId());
		assertEquals(1, r.getId());
	}
}

