package com.censoGenerador.rest;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.censoGenerador.controls.dao.services.CrudRegisterServices;
import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.models.Familia;

@Path("familias")
public class FamiliasAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFamilies() throws Exception {
        HashMap map = new HashMap<>(); //DICCIONARIO QUE SE DEVOLVERA COMO JSON

        FamiliaServices fs = new FamiliaServices(); //SERVICO QUE OFRECE LAS OPERACIONES DE FAMILIA
        CrudRegisterServices crs = new CrudRegisterServices(); //SERVICIO QUE OFRECE LAS OPERACIONES DEL HISTORIAL

        map.put("msg", "OK");
        map.put("data", fs.getListAll().toArray()); //SE AGREGA LA LISTA COMPLETA DE FAMILIAS 

        if (fs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        
        //GUARDA LA OPERACION EN EL HISTORIAL
        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de todas las familias");
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

        //DEVUELVE EL JSON
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveFamilies(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            FamiliaServices fs = new FamiliaServices();
            CrudRegisterServices crs = new CrudRegisterServices();

            fs.getFamilia().setApellido(map.get("apellido").toString());
            fs.getFamilia().setNroIntegrantes(Integer.parseInt(map.get("nroIntegrantes").toString()));
            fs.save();

            res.put("msg", "OK");
            res.put("data", "Famila registrada correctamente");
            
            crs.getRegister().setOperacion("CREATE");
            crs.getRegister().setDetalle("Creacion de la familia: " + fs.getFamilia().getId());
            crs.getRegister().setHora(LocalDateTime.now().toString());
            crs.save();

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamily(@PathParam("id") Integer id) throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        CrudRegisterServices crs = new CrudRegisterServices();
        Familia familia = fs.get(id);

        if (familia == null || familia.getId() == null) {
            map.put("msg", "Error");
            map.put("data", "No existe esa persona");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de la familia: " + fs.getFamilia().getId());
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

        map.put("msg", "OK");
        map.put("data", familia);

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFamilies(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            FamiliaServices fs = new FamiliaServices();
            CrudRegisterServices crs = new CrudRegisterServices();

            fs.setFamilia(fs.get(Integer.parseInt(map.get("id").toString())));
            fs.getFamilia().setApellido(map.get("apellido").toString());
            fs.getFamilia().setNroIntegrantes(Integer.parseInt(map.get("nroIntegrantes").toString()));
            fs.update();

            res.put("msg", "OK");
            res.put("data", "Famila actualizada correctamente");

            crs.getRegister().setOperacion("UPDATE");
            crs.getRegister().setDetalle("Actualizacion de la familia: " + fs.getFamilia().getId());
            crs.getRegister().setHora(LocalDateTime.now().toString());
            crs.save();

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFamilies(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            FamiliaServices fs = new FamiliaServices();
            CrudRegisterServices crs = new CrudRegisterServices();
            
            Integer id = Integer.parseInt(map.get("id").toString());
            Familia familia = fs.get(id);

            if (familia == null || familia.getId() == null) {
                res.put("msg", "Error");
                res.put("data", "No existe esa persona");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            fs.setFamilia(familia);
            fs.delete();

            res.put("msg", "OK");
            res.put("data", "Famila eliminada correctamente");
            
            crs.getRegister().setOperacion("DELETE");
            crs.getRegister().setDetalle("Eliminacion de la familia: "+ familia.getId());
            crs.getRegister().setHora(LocalDateTime.now().toString());
            crs.save();

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
