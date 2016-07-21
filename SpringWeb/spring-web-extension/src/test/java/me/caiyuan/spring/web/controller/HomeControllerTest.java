package me.caiyuan.spring.web.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * YUAN
 * 7/11/16.
 */
public class HomeControllerTest {

    @Test
    public void home() throws Exception {
        HomeController controller = new HomeController();
        assertEquals("home", controller.home());

        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }

}