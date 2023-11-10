package dev.jesus.movies.Services;

import dev.jesus.movies.Entities.Movie;
import dev.jesus.movies.Repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }
    public Optional<Movie> movieById(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
    public URI createMovie(Movie movie){
        Movie savedMovie = movieRepository.save(movie);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMovie.getImdbId()).toUri();
    }
    public Movie deleteMovie(String imdbId){
        Optional<Movie> result = movieRepository.findMovieByImdbId(imdbId);
        if(result.isPresent()){
            return movieRepository.deleteByImdbId(imdbId);
        }
        return null;
    }
    public Movie updateMovie(Movie movie,String imdbId){
        Optional<Movie> movieOptional = movieRepository.findMovieByImdbId(imdbId);
        if(movieOptional.isEmpty())
            return null;
        movie.setId(movieOptional.get().getId());
        movie.setImdbId(imdbId);
        return movieRepository.save(movie);
    }
}
