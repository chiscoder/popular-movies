package me.cristiangomez.popularmovies.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.cristiangomez.popularmovies.R;
import me.cristiangomez.popularmovies.data.pojo.Movie;
import me.cristiangomez.popularmovies.util.Constants;
import me.cristiangomez.popularmovies.util.Utils;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private final List<Movie> mMovies;
    private final Picasso mPicasso;
    private MoviesAdapterListener mListener;

    public MoviesAdapter(List<Movie> movies, Context context) {
        this.mMovies = movies;
        mPicasso = Picasso.with(context);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_movie, parent,
                        false), mPicasso, movie -> {
            if (mListener != null) {
                mListener.onMovieClick(movie);
            }
        });
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setListener(MoviesAdapterListener mListener) {
        this.mListener = mListener;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fl_movie_poster_container)
        ImageView mMoviePosterIv;
        final Picasso mPicasso;
        Movie mMovie;

        MovieViewHolder(View itemView, Picasso picasso, MoviesAdapterListener moviesAdapterListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (moviesAdapterListener != null && mMovie != null) {
                    moviesAdapterListener.onMovieClick(mMovie);
                }
            });
            mPicasso = picasso;
        }

        void bind(Movie movie) {
            mMovie = movie;
            String contentDescription = itemView.getContext().getString(R.string.content_description_movie_poster,
                    movie.getTitle());
            mMoviePosterIv.setContentDescription(contentDescription);
            mPicasso.load(Utils.getImageUri(movie.getPosterPath(), Constants.IMAGE_SIZE))
                    .fit()
                    .centerCrop()
                    .into(mMoviePosterIv);
        }
    }

    interface MoviesAdapterListener {
        void onMovieClick(Movie movie);
    }
}