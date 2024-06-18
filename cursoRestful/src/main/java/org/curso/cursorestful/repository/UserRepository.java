package org.curso.cursorestful.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.curso.cursorestful.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


}
