package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Movie extends Show {
    private int duration;

    public Movie(String title, int year, ArrayList<String> cast, ArrayList<String> genres, int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
    }

    public ArrayList<String> getNLongest_asc(ArrayList<Movie> movieList, int n, String genre, String year) {
        HashMap<String, Integer> movieSize = new HashMap<>();
        ArrayList<Movie> newMovieSize = new ArrayList<>(movieList);
        int counter = 0;
        int an = 0;

        for (int j = 0; j < movieList.size(); j++) {

            if (year != null) {

                an = Integer.parseInt(year);

            }

            if (((movieList.get(j).getYear() == an) || (year == null)) &&
                    ((movieList.get(j).getGenres().contains(genre)) || (genre == null))) {

                movieSize.put(movieList.get(j).getTitle(), movieList.get(j).getDuration());

            }

        }
        newMovieSize.removeIf((mov) -> {
            return (!movieSize.containsKey(mov.getTitle()));
        });

        newMovieSize.sort(sortLongestMovies(movieSize));

        ArrayList<String> finalMovies = new ArrayList<>();

        for (int k = 0; k < n && k < newMovieSize.size(); k++) {
            finalMovies.add(newMovieSize.get(k).getTitle());
        }

        return finalMovies;

    }

    public ArrayList<String> getNLongest_desc(ArrayList<Movie> movieList, int n, String genre, String year) {
        HashMap<String, Integer> movieSize = new HashMap<>();
        ArrayList<Movie> newMovieSize = new ArrayList<>(movieList);
        int counter = 0;
        int an = 0;

        for (int j = 0; j < movieList.size(); j++) {

            if (year != null) {

                an = Integer.parseInt(year);

            }

            if (((movieList.get(j).getYear() == an) || (year == null)) &&
                    ((movieList.get(j).getGenres().contains(genre)) || (genre == null))) {

                movieSize.put(movieList.get(j).getTitle(), movieList.get(j).getDuration());

            }

        }
        newMovieSize.removeIf((mov) -> {
            return (!movieSize.containsKey(mov.getTitle()));
        });

        newMovieSize.sort(sortLongestMovies(movieSize));
        Collections.reverse(newMovieSize);

        ArrayList<String> finalMovies = new ArrayList<>();

        for (int k = 0; k < n && k < newMovieSize.size(); k++) {
            finalMovies.add(newMovieSize.get(k).getTitle());
        }

        return finalMovies;

    }

    public ArrayList<String> getMostViewed_desc(ArrayList<Movie> movieList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> movieViews = new HashMap<>();
        ArrayList<Movie> newMovieViews = new ArrayList<>(movieList);
        for (int j = 0; j < movieList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((movieList.get(j).getYear() == an) || (year == null)) &&
                    ((movieList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getHistory().get(movieList.get(j).getTitle()) != null)
                        counter += userList.get(k).getHistory().get(movieList.get(j).getTitle());
                }
                if (counter != 0)
                    movieViews.put(movieList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newMovieViews.removeIf((mov) -> {
            return (!movieViews.containsKey(mov.getTitle()));
        });

        newMovieViews.sort(sortViewMovies(movieViews));
        Collections.reverse(newMovieViews);
        ArrayList<String> finalMovies = new ArrayList<>();
        for (int k = 0; k < n && k < newMovieViews.size(); k++) {

            finalMovies.add(newMovieViews.get(k).getTitle());

        }

        return finalMovies;
    }

    public ArrayList<String> getMostViewed_asc(ArrayList<Movie> movieList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> movieViews = new HashMap<>();
        ArrayList<Movie> newMovieViews = new ArrayList<>(movieList);
        for (int j = 0; j < movieList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if ((movieList.get(j).getYear() == an || (year == null)) &&
                    (movieList.get(j).getGenres().contains(genre) || genre == null)) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getHistory().get(movieList.get(j).getTitle()) != null)
                        counter += userList.get(k).getHistory().get(movieList.get(j).getTitle());
                }
                if (counter != 0)
                    movieViews.put(movieList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newMovieViews.removeIf((mov) -> {
            return (!movieViews.containsKey(mov.getTitle()));
        });

        newMovieViews.sort(sortViewMovies(movieViews));
        ArrayList<String> finalMovies = new ArrayList<>();
        for (int k = 0; k < n && k < newMovieViews.size(); k++) {

            finalMovies.add(newMovieViews.get(k).getTitle());

        }

        return finalMovies;
    }

    public ArrayList<String> getNFavorite_asc(ArrayList<Movie> movieList, ArrayList<User> userList, int n, String genre, String year) {
        HashMap<String, Integer> movieFavorites = new HashMap<>();
        ArrayList<Movie> newMovieViews = new ArrayList<>(movieList);
        for (int j = 0; j < movieList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((movieList.get(j).getYear() == an) || (year == null)) &&
                    ((movieList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getFavoriteMovies().contains(movieList.get(j).getTitle()) != false)
                        counter += 1;
                }
                if (counter != 0)
                    movieFavorites.put(movieList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newMovieViews.removeIf((mov) -> {
            return (!movieFavorites.containsKey(mov.getTitle()));
        });

        newMovieViews.sort(sortViewMovies(movieFavorites));
        ArrayList<String> finalMovies = new ArrayList<>();
        for (int k = 0; k < n && k < newMovieViews.size(); k++) {

            finalMovies.add(newMovieViews.get(k).getTitle());

        }

        return finalMovies;
    }

    public ArrayList<String> getNFavorite_desc(ArrayList<Movie> movieList, ArrayList<User> userList, int n, String genre, String year) {
        HashMap<String, Integer> movieFavorites = new HashMap<>();
        ArrayList<Movie> newMovieViews = new ArrayList<>(movieList);
        for (int j = 0; j < movieList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((movieList.get(j).getYear() == an) || (year == null)) &&
                    ((movieList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getFavoriteMovies().contains(movieList.get(j).getTitle()) != false)
                        counter += 1;
                }
                if (counter != 0)
                    movieFavorites.put(movieList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newMovieViews.removeIf((mov) -> {
            return (!movieFavorites.containsKey(mov.getTitle()));
        });

        newMovieViews.sort(sortViewMovies(movieFavorites));
        Collections.reverse(newMovieViews);
        ArrayList<String> finalMovies = new ArrayList<>();
        for (int k = 0; k < n && k < newMovieViews.size(); k++) {

            finalMovies.add(newMovieViews.get(k).getTitle());

        }

        return finalMovies;
    }


    public ArrayList<String> getBestRating_desc(ArrayList<Movie> movieList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> movieRating = new HashMap<>();
        ArrayList<Movie> newMovieRating = new ArrayList<>(movieList);
        for (int j = 0; j < movieList.size(); j++) {
            int rating = 0;
            int an = 0;
            int count_apparitions = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((movieList.get(j).getYear() == an) || (year == null)) &&
                    ((movieList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getRating().get(movieList.get(j).getTitle()) != null)
                        rating += userList.get(k).getRating().get(movieList.get(j).getTitle());
                    count_apparitions++;
                }
                if (rating != 0) {
                    rating = rating / count_apparitions;
                    movieRating.put(movieList.get(j).getTitle(), rating);
                }
                rating = 0;
            }
        }
        newMovieRating.removeIf((mov) -> {
            return (!movieRating.containsKey(mov.getTitle()));
        });

        newMovieRating.sort(sortViewMovies(movieRating));
        Collections.reverse(newMovieRating);
        ArrayList<String> finalMovies = new ArrayList<>();
        for (int k = 0; k < n && k < newMovieRating.size(); k++) {

            finalMovies.add(newMovieRating.get(k).getTitle());

        }

        return finalMovies;
    }


    public ArrayList<String> getBestRating_asc(ArrayList<Movie> movieList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> movieRating = new HashMap<>();
        ArrayList<Movie> newMovieRating = new ArrayList<>(movieList);
        for (int j = 0; j < movieList.size(); j++) {
            int rating = 0;
            int an = 0;
            int count_apparitions = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((movieList.get(j).getYear() == an) || (year == null)) &&
                    ((movieList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getRating().get(movieList.get(j).getTitle()) != null)
                        rating += userList.get(k).getRating().get(movieList.get(j).getTitle());
                    count_apparitions++;
                }
                if (rating != 0) {
                    rating = rating / count_apparitions;
                    movieRating.put(movieList.get(j).getTitle(), rating);
                }
                rating = 0;
            }
        }
        newMovieRating.removeIf((mov) -> {
            return (!movieRating.containsKey(mov.getTitle()));
        });

        newMovieRating.sort(sortViewMovies(movieRating));
        ArrayList<String> finalMovies = new ArrayList<>();
        for (int k = 0; k < n && k < newMovieRating.size(); k++) {

            finalMovies.add(newMovieRating.get(k).getTitle());

        }

        return finalMovies;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static Comparator<Movie> sortLongestMovies(HashMap<String, Integer> movieSize) {
        Comparator comp = (Comparator<Movie>) (movi, movf) -> {
            int criteriu = movieSize.get(movi.getTitle()) - movieSize.get(movf.getTitle());
            if (criteriu == 0) {
                return movi.getTitle().compareTo(movf.getTitle());
            }
            return criteriu;
        };
        return comp;
    }

    public static Comparator<Movie> sortViewMovies(HashMap<String, Integer> movieSize) {
        Comparator comp = (Comparator<Movie>) (movi, movf) -> {
            int criteriu = movieSize.get(movi.getTitle()) - movieSize.get(movf.getTitle());
            if (criteriu == 0) {
                return movi.getTitle().compareTo(movf.getTitle());
            }
            return criteriu;
        };
        return comp;
    }
}
