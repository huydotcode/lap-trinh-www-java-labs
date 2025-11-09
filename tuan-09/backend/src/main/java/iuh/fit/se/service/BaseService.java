package iuh.fit.se.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <ID, Request, Response> {
    List<Response> findAll();
    Optional<Response> findById(ID id);
    Response create(Request request);
    Response update(ID id, Request request);
    void delete(ID id);
}
