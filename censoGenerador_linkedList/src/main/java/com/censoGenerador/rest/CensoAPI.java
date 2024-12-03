package com.censoGenerador.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.censoGenerador.controls.dao.services.CensoServices;
import com.censoGenerador.controls.dao.services.CrudRegisterServices;
import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.estructures.list.LinkedList;
import com.censoGenerador.models.Familia;

import java.util.HashMap;
import java.time.LocalDateTime;

@Path("censoAPI")
public class CensoAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliesWithGenerador() throws Exception {
        HashMap map = new HashMap<>();
        try {
            CensoServices cs = new CensoServices();
            CrudRegisterServices crs = new CrudRegisterServices();
    
            cs.determinarFamiliasConGenerador();
            //cs.saveFamiliasConGenerador();
    
            LinkedList<Familia> familiasConGenerador = cs.getFamiliasConGenerador();
    
            if (familiasConGenerador == null || familiasConGenerador.isEmpty()) {
                map.put("msg", "OK");
                map.put("data", new Object[]{});
            } else {
                //System.out.println(cs.getListAll().toString());
                map.put("msg", "OK");
                map.put("data", familiasConGenerador.toArray());
            }

            crs.getRegister().setOperacion("READ");
            crs.getRegister().setDetalle("Lectura de todas las familias con generador");
            crs.getRegister().setHora(LocalDateTime.now().toString());
            crs.save();

            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "ERROR");
            map.put("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/order/shellsort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByShellSort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        CensoServices cs = new CensoServices();
        map.put("msg", "OK");
        
        try {
            LinkedList data = cs.orderByShellSort(attribute, type);
            System.out.println(data.toString());
            map.put("data", data.toArray());
            if (data.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/order/mergesort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByMergeSort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        CensoServices cs = new CensoServices();
        map.put("msg", "OK");
        
        try {
            LinkedList data = cs.orderByMergeSort(attribute, type);
            map.put("data", data.toArray());
            if (data.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/order/quicksort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByQuickSort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        CensoServices cs = new CensoServices();
        map.put("msg", "OK");
        
        try {
            LinkedList data = cs.orderByQuickSort(attribute, type);
            map.put("data", data.toArray());
            if (data.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/historial")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistorial() throws Exception {
        HashMap map = new HashMap<>();
        CrudRegisterServices crs = new CrudRegisterServices();

        map.put("msg", "OK");
        map.put("data", crs.getListAll().toArray());

        if (crs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de todo el historial");
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

        return Response.ok(map).build();
    }

}
