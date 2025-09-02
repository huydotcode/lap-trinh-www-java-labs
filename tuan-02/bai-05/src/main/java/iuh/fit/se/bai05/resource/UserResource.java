package iuh.fit.se.bai05.resource;

import iuh.fit.se.bai05.entity.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static List<User> users = new ArrayList<>();
    private static int idCounter = 1;

    static {
        users.add(new User(idCounter++, "Nguyen", "An", "2000-01-01"));
        users.add(new User(idCounter++, "Tran", "Binh", "1999-05-20"));
    }

    // GET /api/users
    @GET
    public List<User> getAllUsers() {
        return users;
    }

    // GET /api/users/{id}
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .map(u -> Response.ok(u).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // POST /api/users
    @POST
    public Response createUser(User user) {
        user.setId(idCounter++);
        users.add(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    // PUT /api/users/{id}
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id, User updatedUser) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setFirstName(updatedUser.getFirstName());
                u.setLastName(updatedUser.getLastName());
                u.setDob(updatedUser.getDob());
                return Response.ok(u).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // DELETE /api/users/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (removed) {
            return Response.noContent().build(); // 204
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
