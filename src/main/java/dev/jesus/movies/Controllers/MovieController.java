package dev.jesus.movies.Controllers;

import dev.jesus.movies.Entities.Movie;
import dev.jesus.movies.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.allMovies());
    }
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable String imdbId){
        return ResponseEntity.ok(movieService.movieById(imdbId));
    }
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        return ResponseEntity.created(movieService.createMovie(movie)).build();
    }
    @DeleteMapping("/{imdbId}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable String imdbId){
        if(movieService.deleteMovie(imdbId)!=null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{imdbId}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie,@PathVariable String imdbId){
        if(movieService.updateMovie(movie,imdbId)==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();

    }
}
