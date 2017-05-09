package com.tickettracker.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Pooja Mantri
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tickettracker")
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
