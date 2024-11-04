package com.censoGenerador.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.censoGenerador.controls.dao.services.CensoServices;
import com.censoGenerador.controls.dao.services.CrudRegisterServices;
import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.list.ListArray;
import com.censoGenerador.models.Familia;

import java.util.HashMap;

@Path("censoAPI")
public class CensoAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliesWithGenerador() throws Exception {
        HashMap map = new HashMap<>();
        try {
            CensoServices cs = new CensoServices();
            FamiliaServices fs = new FamiliaServices();
    
            ListArray<Familia> familias = fs.getListAll();
            cs.getCenso().setFamilias(familias);
            cs.getCenso().determinarFamiliasConGenerador();
            cs.saveFamiliasConGenerador();
    
            ListArray<Familia> familiasConGenerador = cs.getCenso().getFamiliasConGenerador();
    
            if (familiasConGenerador == null || familiasConGenerador.isEmpty() || familias == null || familias.isEmpty()) {
                map.put("msg", "OK");
                map.put("data", new Object[]{});
            } else {
                map.put("msg", "OK");
                map.put("data", familiasConGenerador);
            }
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "ERROR");
            map.put("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/historial")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistorial() {
        HashMap map = new HashMap<>();
        CrudRegisterServices crs = new CrudRegisterServices();

        map.put("msg", "OK");
        map.put("data", crs.getListAll().toArray());

        if (crs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }

        return Response.ok(map).build();
    }

}
