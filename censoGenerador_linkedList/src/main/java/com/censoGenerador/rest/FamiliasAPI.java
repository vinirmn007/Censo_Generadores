package com.censoGenerador.rest;

import java.util.HashMap;
import java.time.LocalDateTime;

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
import com.censoGenerador.estructures.list.LinkedList;
import com.censoGenerador.models.Familia;

@Path("familias")
public class FamiliasAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFamilies() throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        CrudRegisterServices crs = new CrudRegisterServices();

        map.put("msg", "OK");
        map.put("data", fs.getListAll().toArray());

        if (fs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        
        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de todas las familias");
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

        return Response.ok(map).build();
    }

    @Path("/order/shellsort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByShellSort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        CrudRegisterServices crs = new CrudRegisterServices();

        map.put("msg", "OK");
        
        try {
            LinkedList data = fs.orderByShellSort(attribute, type);
            map.put("data", data.toArray());
            if (data.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de todas las familias ordenadas por: " + attribute);
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

        return Response.ok(map).build();
    }

    @Path("/order/mergesort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByMergeSort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        CrudRegisterServices crs = new CrudRegisterServices();

        map.put("msg", "OK");
        
        try {
            LinkedList data = fs.orderByMergeSort(attribute, type);
            map.put("data", data.toArray());
            if (data.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de todas las familias ordenadas por: " + attribute);
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();
        
        return Response.ok(map).build();
    }

    @Path("/order/quicksort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByQuickSort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        CrudRegisterServices crs = new CrudRegisterServices();

        map.put("msg", "OK");
        
        try {
            LinkedList data = fs.orderByQuickSort(attribute, type);
            map.put("data", data.toArray());
            if (data.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de todas las familias ordenadas por: " + attribute);
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

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

    @Path("/search/lineal/{attribute}/{type}/{value}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response linealSearch(@PathParam("attribute") String attribute, @PathParam("type") Integer type, @PathParam("value") String value) throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        CrudRegisterServices crs = new CrudRegisterServices();

        try {
            map.put("msg", "OK");

            if (type == 1) {
                LinkedList data = fs.getListAll().multipleLinealSearch(attribute, value);
                map.put("data", data.toArray());

                if (data.isEmpty()) {
                    map.put("data", "No existe");
                    return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
                }
            } else {
                Familia data = (Familia) fs.getListAll().atomicLinealSearch(attribute, value);
                    map.put("data", data);

                    if (data == null) {
                        map.put("data", "No existe");
                        return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
                    }
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Busqueda Lineal de la familia: " + value);
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

        return Response.ok(map).build();
    }

    @Path("/search/binary/{attribute}/{type}/{value}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response binarySearch(@PathParam("attribute") String attribute, @PathParam("type") Integer type, @PathParam("value") String value) throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        CrudRegisterServices crs = new CrudRegisterServices();

        try {
            map.put("msg", "OK");

            Familia[] data = new Familia[1];
            data[0] = (Familia) fs.getListAll().binarySearch(attribute, value);

            map.put("data", data);

            if (data[0] == null) {
                map.put("data", "No existe");
                return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
            }
            
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Busqueda Binaria de la familia: " + value);
        crs.getRegister().setHora(LocalDateTime.now().toString());
        crs.save();

        return Response.ok(map).build();
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
