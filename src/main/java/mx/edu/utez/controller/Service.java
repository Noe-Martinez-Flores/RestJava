package mx.edu.utez.controller;

import mx.edu.utez.model.Employee;
import mx.edu.utez.server.DataBase;

import javax.jws.WebParam;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.List;

@Path("/employee")
public class Service {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees(){
        DataBase dataBase = new DataBase();
        List<Employee> employees = dataBase.readAll();;
        return employees;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") int id){
        DataBase dataBase = new DataBase();
        return dataBase.readOne(id);

    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean createEmployee(Employee employee){
        DataBase dataBase = new DataBase();
        return dataBase.addEmployee(employee);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean update (@PathParam("id") int id, String lastName, String firstName, String extension, String email, String officeCode, int reportsTo, String jobTitle) {
        DataBase dataBase = new DataBase();
        return dataBase.updateEmployee(lastName, firstName, extension, email, officeCode, reportsTo, jobTitle, id);
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteEmployee (@PathParam("id") int id){
        DataBase dataBase = new DataBase();
         return dataBase.delete(id);
    }


}
