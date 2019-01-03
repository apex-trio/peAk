package com.apextrio.peak;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class PeakApplicationTests {

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
	@WithUserDetails("NJCrain1")
	public void testTeamsId() {
		try {
			MvcResult response = this.mvc.perform(get("/teams/3")).andExpect(status().isOk()).andExpect(view().name("team")).andExpect(model().attribute("inGroup", false)).andReturn();
			assertTrue(response.getResponse().getContentAsString().contains("<div id=\"users\">"));
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
	@WithUserDetails("NJCrain1")
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

	//---------------------------------
	//TESTING FOR APPLICATION ENDPOINTS
	//---------------------------------
}

