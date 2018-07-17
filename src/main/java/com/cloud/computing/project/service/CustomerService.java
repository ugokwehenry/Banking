package com.cloud.computing.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import com.cloud.computing.project.customer.persistence.CustomerDAO;
import com.cloud.computing.project.customer.persistence.CustomerDAOFactory;
import com.cloud.computing.project.exception.UserNotFoundException;
import com.cloud.computing.project.service.ResponseBuilder;
import com.cloud.computing.project.user.data.JsonSerializable;

@DeclareRoles({"admin", "user", "guest"})
@Path("/customer")
public class CustomerService extends  ResourceConfig{
	final static Logger logger = Logger.getLogger(CustomerService.class);
	
	
	@GET
	@Path("/getAll")
	@RolesAllowed({"admin"}) // only an admin user should be allowed to request all users
	@Produces("application/json")
	public Response getAll( @Context HttpHeaders headers ) {
		logger.debug( "getCustomer: " + headers  );
		CustomerDAO customerDao = CustomerDAOFactory.getCustomerDAO();
		
		try {		
			boolean isNull = isNullOrEmpty(customerDao.getAllCustomers());
			System.out.println("isNull:-"+isNull);
			List<JsonSerializable> customersJson = new ArrayList<JsonSerializable>();
			customersJson.addAll(  (Collection<? extends JsonSerializable>) customerDao.getAllCustomers() );
			
			// Return the customers on the response
			return ResponseBuilder.createResponse( Response.Status.OK, customersJson );
		}
		catch( UserNotFoundException e ) {
			return ResponseBuilder.createResponse( Response.Status.NOT_FOUND, e.getMessage() );
		}
		catch ( Exception e ) {	
			logger.debug(e.getClass().getName() +": "+e.getMessage());
			e.printStackTrace();
			return ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED );
		}
		
	}
	
	public static boolean isNullOrEmpty( final Collection< ? > c ) {
	    return c == null || c.isEmpty();
	}
}
