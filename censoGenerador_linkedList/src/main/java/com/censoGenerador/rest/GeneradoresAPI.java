package com.censoGenerador.rest;

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
import com.censoGenerador.controls.dao.services.GeneradorServices;
import com.censoGenerador.models.Familia;
import com.censoGenerador.models.Generador;

@Path("/generadores")
public class GeneradoresAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGeneradores() throws Exception {
        HashMap map = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        CrudRegisterServices crs = new CrudRegisterServices();

        map.put("msg", "OK");
        map.put("data", gs.getListAll().toArray());

        if (gs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura de todos los generadores");
        crs.save();

        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveGenerator(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            GeneradorServices gs = new GeneradorServices();
            FamiliaServices fs = new FamiliaServices();
            CrudRegisterServices crs = new CrudRegisterServices();

            Familia familia = fs.get(Integer.parseInt(map.get("familiaId").toString()));
            if (familia == null) {
                res.put("msg", "Error");
                res.put("data", "Familia no encontrada");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            gs.getGenerador().setMarca(map.get("marca").toString());
            gs.getGenerador().setModelo(map.get("modelo").toString());
            gs.getGenerador().setConsumoPorHora(Float.parseFloat(map.get("consumo").toString()));
            gs.getGenerador().setEnergiaGenerada(Float.parseFloat(map.get("energia").toString()));
            gs.getGenerador().setPrecio(Float.parseFloat(map.get("precio").toString()));
            gs.getGenerador().setUso(map.get("uso").toString());
            gs.getGenerador().setFamilia(familia);

            gs.save();

            familia.setGeneradorId(gs.getGenerador().getId());
            fs.setFamilia(familia);
            fs.update();

            res.put("msg", "OK");
            res.put("data", "Generador registrado y asignado a familia correctamente");

            crs.getRegister().setOperacion("CREATE");
            crs.getRegister().setDetalle("Creacion del generador: "+ gs.getGenerador().getId());
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
    public Response getGenerator(@PathParam("id") Integer id) throws Exception {
        HashMap map = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        CrudRegisterServices crs = new CrudRegisterServices();
        Generador generador = gs.get(id);

        if (generador == null || generador.getId() == null) {
            map.put("msg", "Error");
            map.put("data", "No existe ese generador");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", generador);

        crs.getRegister().setOperacion("READ");
        crs.getRegister().setDetalle("Lectura del generador: "+ gs.getGenerador().getId());
        crs.save();

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGenerator(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            GeneradorServices gs = new GeneradorServices();
            CrudRegisterServices crs = new CrudRegisterServices();

            gs.setGenerador(gs.get(Integer.parseInt(map.get("id").toString())));
            gs.getGenerador().setMarca(map.get("marca").toString());
            gs.getGenerador().setModelo(map.get("modelo").toString());
            gs.getGenerador().setConsumoPorHora(Float.parseFloat(map.get("consumo").toString()));
            gs.getGenerador().setEnergiaGenerada(Float.parseFloat(map.get("energia").toString()));
            gs.getGenerador().setPrecio(Float.parseFloat(map.get("precio").toString()));
            gs.getGenerador().setUso(map.get("uso").toString());
            gs.update();

            res.put("msg", "OK");
            res.put("data", "Generador actualizado correctamente");

            crs.getRegister().setOperacion("UPDATE");
            crs.getRegister().setDetalle("Actualizacion del generador: "+ gs.getGenerador().getId());
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
    public Response deleteGenerator(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            GeneradorServices gs = new GeneradorServices();
            CrudRegisterServices crs = new CrudRegisterServices();
            Integer id = Integer.parseInt(map.get("id").toString());
            Generador generador = gs.get(id);

            if (generador == null || generador.getId() == null) {
                res.put("msg", "Error");
                res.put("data", "No existe esa persona");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            gs.setGenerador(generador);
            gs.delete();

            res.put("msg", "OK");
            res.put("data", "Famila eliminada correctamente");

            crs.getRegister().setOperacion("DELETE");
            crs.getRegister().setDetalle("Eliminacion del generador: "+ generador.getId());
            crs.save();

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
