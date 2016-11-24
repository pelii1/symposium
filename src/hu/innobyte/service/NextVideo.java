package hu.innobyte.service;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import hu.innobyte.dao.Dao;

@Path("/")
public class NextVideo {
	public static final String INITPARAMS_FILE = "files\\InitParams.xml";
	@Context
	private ServletContext context;
	
	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/beertap")
	public Response nextVideo() {
		
		int value = Dao.runStoredProcedure("");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status","ok");
		jsonObject.put("sceneid",value);
		
		/*System.out.println(context.getRealPath("/") + INITPARAMS_FILE);
		InitParams initParams = InitParams.loadFromFile(context.getRealPath("/") + INITPARAMS_FILE);
		System.out.println(initParams.getHost() + ":" + initParams.getPort() + " " + initParams.getDatabaseName() + " " + initParams.getUserName() + " " + initParams.getPassword());*/
		
		return Response.status(Status.OK).entity(jsonObject.toString()).build();
	}
}
