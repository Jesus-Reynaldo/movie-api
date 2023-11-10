package dev.jesus.movies.Repositories;

import dev.jesus.movies.Entities.Movie;
import dev.jesus.movies.Entities.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
