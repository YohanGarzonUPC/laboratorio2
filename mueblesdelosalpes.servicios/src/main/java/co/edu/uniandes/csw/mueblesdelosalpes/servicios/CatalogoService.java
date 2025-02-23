/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCarritoMockRemote;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/Catalogo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogoService {
 
    /**
     * Referencia al Ejb del catalogo encargada de realizar las operaciones del mismo.
     */
    @EJB
    private IServicioCatalogoMockLocal catalogoEjb;
   
 
    /**
     * Servicio que ofrece una lista JSON con el catálogo de Muebles de los alpes (Los muebles disponibles para la venta).
     * @return la lista JSON con los muebles de MDLA.
  
     */
    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return catalogoEjb.darMuebles();
 
    }
    @POST
    @Path("agregar/")
    public List<Mueble> agregarMuebles(List<Mueble> mb) {
        for (Mueble mueble : mb) {
            catalogoEjb.agregarMueble(mueble);
        }
        return mb;
    }
    @DELETE
    @Path("eliminar/{id}")
    public Response eliminarMueble(@PathParam("id") long id) {
        catalogoEjb.eliminarMueble(id);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("remover/{id}")
    public Response removerEjemplarMueble(@PathParam("id") long id) {
        catalogoEjb.removerEjemplarMueble(id);
        return Response.status(Response.Status.OK).build();
    }
 
}
