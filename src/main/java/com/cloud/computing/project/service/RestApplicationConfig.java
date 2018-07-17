package com.cloud.computing.project.service;

import org.glassfish.jersey.server.ResourceConfig;

import com.cloud.computing.project.filter.AuthenticationFilter;

/**
 *  set the filter applications manually and not via web.xml
 */
public class RestApplicationConfig extends ResourceConfig {
	
	public RestApplicationConfig() {
        packages( "com.cloud.computing.project.filter" );
		register( AuthenticationFilter.class );
	}
}
