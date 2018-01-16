package me.cristiangomez.popularmovies.network;

import io.reactivex.Observable;
import me.cristiangomez.popularmovies.data.pojo.Movie;
import me.cristiangomez.popularmovies.data.pojo.MoviesResponse;
import me.cristiangomez.popularmovies.network.responses.CastResponse;
import me.cristiangomez.popularmovies.network.responses.ImagesResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbApi {
    @GET("/3/movie/top_rated")
    Observable<Response<MoviesResponse>> topRatedMovies(@Query("api_key") String apiKey,
                                                        @Query("language") String language,
                                                        @Query("page") int page);

    @GET("/3/movie/popular")
    Observable<Response<MoviesResponse>> popularMovies(@Query("api_key") String apiKey,
                                                        @Query("language") String language,
                                                        @Query("page") int page);

    @GET("/3/movie/{id}")
    Observable<Response<Movie>> getMovie(@Path("id") int id,
                                         @Query("api_key") String apiKey,
                                         @Query("language") String language);

    @GET("/3/movie/{id}/images")
    Observable<Response<ImagesResponse>> getMoviePhotos(@Path("id") int id,
                                                        @Query("api_key") String apiKey,
                                                        @Query("language") String language);

    @GET("/3/movie/{id}/credits")
    Observable<Response<CastResponse>> getMovieCast(@Path("id") int id,
                                                    @Query("api_key") String apiKey);

}
