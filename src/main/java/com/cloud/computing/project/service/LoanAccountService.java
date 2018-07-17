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
import com.cloud.computing.project.exception.UserNotFoundException;
import com.cloud.computing.project.loan.account.data.LoanAccountDAOFactory;
import com.cloud.computing.project.loan.account.persistence.LoanAccountDAO;
import com.cloud.computing.project.user.data.JsonSerializable;

@DeclareRoles({"admin", "user", "guest"})
@Path("/account/loan")
public class LoanAccountService {
final static Logger logger = Logger.getLogger(LoanAccountService.class);
	
	@GET
	@Path("/getAll")
	@RolesAllowed({"admin"}) // only an admin user should be allowed to request all users
	@Produces("application/json")
	public Response getAll( @Context HttpHeaders headers ) {
		logger.debug( "getAllLoanAccounts: " + headers  );
		LoanAccountDAO lnAccountDao = LoanAccountDAOFactory.getLoanAccountDAO();
		
		try {		
			List<JsonSerializable> loanAccountsJson = new ArrayList<JsonSerializable>();
			loanAccountsJson.addAll(  (Collection<? extends JsonSerializable>) lnAccountDao.getAllLoanAccount() );
			
			// Return the deposit accounts on the response
			return ResponseBuilder.createResponse( Response.Status.OK, loanAccountsJson );
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
}
