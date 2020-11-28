package classes;

import java.util.ArrayList;
import java.util.Map;

public class User {
    private String username;
    private String subscriptionType;
    private Map<String, Integer> history;
    private ArrayList<String> favoriteMovies;
    private Map<String, Double> ratings;

    public User(String username, String subscriptionType, Map<String, Integer> history, ArrayList<String> favoriteMovies, Map<String, Double> ratings) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.history = history;
        this.favoriteMovies = favoriteMovies;
        this.ratings = ratings;
    }

    public String addFavorite(String showname) {
        if (this.history.get(showname)==null) {
            return "error -> " +showname+ " is not seen";

        } else {
            for(int i=0; i<this.favoriteMovies.size(); i++) {
                if (this.favoriteMovies.get(i).equals(showname)) {
                    return "error -> " +showname+ " is already in favourite list";
                }
            }
            this.favoriteMovies.add(showname);
            return "success -> " +showname+ " was added as favourite";
        }
    }

    public String addViewed(String showname) {
        if (this.history.get(showname)==null) {
            this.history.put(showname, 1);
        } else {
            this.history.replace(showname, this.history.get(showname), this.history.get(showname)+1);
        }
        return "success -> " +showname+ " was viewed with total views of " + this.history.get(showname);
    }

    public String addRating(String showname, double rating) {
        if (this.history.get(showname)==null) {
            return "error -> " +showname+ " is not seen";
        } else {
            this.ratings.put(showname, rating);
            return "success -> " +showname+ " was rated with " +rating+ " by " +this.username;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public void setHistory(Map<String, Integer> history) {
        this.history = history;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(ArrayList<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public Map<String, Double> getRating() {
        return ratings;
    }

    public void setRating(Map<String, Double> rating) {
        this.ratings = rating;
    }
}
