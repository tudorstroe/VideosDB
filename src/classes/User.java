package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class User {
    private final String username;
    private final String subscriptionType;
    private final Map<String, Integer> history;
    private final ArrayList<String> favoriteMovies;
    private final Map<String, Double> ratings;

    public User(final String username, final String subscriptionType,
                final Map<String, Integer> history, final ArrayList<String> favoriteMovies,
                final Map<String, Double> ratings) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.history = history;
        this.favoriteMovies = favoriteMovies;
        this.ratings = ratings;
    }

    public User() {
        this.username = "";
        this.subscriptionType = "";
        this.history = new HashMap<String, Integer>();
        this.favoriteMovies = new ArrayList<String>();
        this.ratings = new HashMap<String, Double>();
    }

    /**
     * adauga la favorite un show
     * @param showname
     * @return
     */
    public String addFavorite(final String showname) {
        if (this.history.get(showname) == null) {
            return "error -> " + showname + " is not seen";

        } else {
            for (int i = 0; i < this.favoriteMovies.size(); i++) {
                if (this.favoriteMovies.get(i).equals(showname)) {
                    return "error -> " + showname + " is already in favourite list";
                }
            }
            this.favoriteMovies.add(showname);
            return "success -> " + showname + " was added as favourite";
        }
    }

    /**
     * adauga la vizualizate un serial
     * @param showname
     * @return
     */
    public String addViewed(final String showname) {
        if (this.history.get(showname) == null) {
            this.history.put(showname, 1);
        } else {
            this.history.replace(showname, this.history.get(showname),
                    this.history.get(showname) + 1);
        }
        return "success -> " + showname + " was viewed with total views of "
                + this.history.get(showname);
    }

    /**
     * adauga un rating pt un user la un anume show
     * @param showname
     * @param rating
     * @return
     */
    public String addRating(final String showname, final double rating) {
        if (this.history.get(showname) == null) {
            return "error -> " + showname + " is not seen";
        } else {
            this.ratings.put(showname, rating);
            return "success -> " + showname + " was rated with " + rating + " by " + this.username;
        }
    }

    /**
     * recomandarea standard.
     * @param usernameF
     * @param movies
     * @param userList
     * @return
     */
    public String standardRec(final String usernameF, final ArrayList<Movie> movies,
                                    final ArrayList<User> userList) {
        User user = new User();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(usernameF)) {
                user = userList.get(i);
            }

        }
        for (int i = 0; i < movies.size(); i++) {
            if (user.history.get(movies.get(i).getTitle()) == null) {
                return movies.get(i).getTitle();
            }
        }
        return "";
    }

    public String getUsername() {
        return username;
    }


    public String getSubscriptionType() {
        return subscriptionType;
    }


    public Map<String, Integer> getHistory() {
        return history;
    }


    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }


    public Map<String, Double> getRating() {
        return ratings;
    }

}

