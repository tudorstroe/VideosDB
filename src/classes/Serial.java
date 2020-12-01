package classes;

import entertainment.Season;

import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Serial extends Show {
    private int numberOfSeasons;
    private ArrayList<Season> seasons;

    public Serial(String title, int year, ArrayList<String> cast, ArrayList<String> genres, int numberOfSeasons, ArrayList<Season> seasons) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public ArrayList<String> getMostViewed_serial_desc(ArrayList<Serial> serialList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> serialViews = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<Serial>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (serialList.get(j).getYear() == an &&
                    serialList.get(j).getGenres().contains(genre)) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getHistory().get(serialList.get(j).getTitle()) != null)
                        counter += userList.get(k).getHistory().get(serialList.get(j).getTitle());
                }
                if (counter != 0)
                    serialViews.put(serialList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newSerialViews.removeIf((mov) -> {
            return (!serialViews.containsKey(mov.getTitle()));
        });

        newSerialViews.sort(sortViewSerials(serialViews));
        Collections.reverse(newSerialViews);
        ArrayList<String> finalSerials = new ArrayList<>();
        for (int k = 0; k < n && k < newSerialViews.size(); k++) {

            finalSerials.add(newSerialViews.get(k).getTitle());

        }

        return finalSerials;
    }

    public ArrayList<String> getMostViewed_serial_asc(ArrayList<Serial> serialList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> serialViews = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<Serial>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (serialList.get(j).getYear() == an &&
                    serialList.get(j).getGenres().contains(genre)) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getHistory().get(serialList.get(j).getTitle()) != null)
                        counter += userList.get(k).getHistory().get(serialList.get(j).getTitle());
                }
                if (counter != 0)
                    serialViews.put(serialList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newSerialViews.removeIf((mov) -> {
            return (!serialViews.containsKey(mov.getTitle()));
        });

        newSerialViews.sort(sortViewSerials(serialViews));
        ArrayList<String> finalSerials = new ArrayList<>();
        for (int k = 0; k < n && k < newSerialViews.size(); k++) {

            finalSerials.add(newSerialViews.get(k).getTitle());

        }

        return finalSerials;
    }

    public ArrayList<String> getNLongest_desc(ArrayList<Serial> serialList, int n, String genre, String year) {
        HashMap<String, Integer> serialSize = new HashMap<>();
        ArrayList<Serial> newSerialSize = new ArrayList<>(serialList);
        int counter = 0;
        int an = 0;
        int size_aux = 0;
        for (int j = 0; j < serialList.size(); j++) {

            if (year != null) {

                an = Integer.parseInt(year);

            }

            if (((serialList.get(j).getYear() == an) || (year == null)) &&
                    ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {
                size_aux = 0;
                for (int k = 0; k < serialList.get(j).numberOfSeasons; k++) {

                    size_aux += serialList.get(j).getSeasons().get(k).getDuration();

                }

                serialSize.put(serialList.get(j).getTitle(), size_aux);


            }

        }
        newSerialSize.removeIf((mov) -> {
            return (!serialSize.containsKey(mov.getTitle()));
        });

        newSerialSize.sort(sortViewSerials(serialSize));
        Collections.reverse(newSerialSize);

        ArrayList<String> finalSerials = new ArrayList<>();

        for (int k = 0; k < n && k < newSerialSize.size(); k++) {
            finalSerials.add(newSerialSize.get(k).getTitle());
        }

        return finalSerials;

    }

    public ArrayList<String> getNLongest_asc(ArrayList<Serial> serialList, int n, String genre, String year) {
        HashMap<String, Integer> serialSize = new HashMap<>();
        ArrayList<Serial> newSerialSize = new ArrayList<>(serialList);
        int counter = 0;
        int an = 0;
        int size_aux = 0;
        for (int j = 0; j < serialList.size(); j++) {

            if (year != null) {

                an = Integer.parseInt(year);

            }

            if (((serialList.get(j).getYear() == an) || (year == null)) &&
                    ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {
                size_aux = 0;
                for (int k = 0; k < serialList.get(j).numberOfSeasons; k++) {

                    size_aux += serialList.get(j).getSeasons().get(k).getDuration();

                }

                serialSize.put(serialList.get(j).getTitle(), size_aux);


            }

        }
        newSerialSize.removeIf((mov) -> {
            return (!serialSize.containsKey(mov.getTitle()));
        });

        newSerialSize.sort(sortViewSerials(serialSize));

        ArrayList<String> finalSerials = new ArrayList<>();

        for (int k = 0; k < n && k < newSerialSize.size(); k++) {
            finalSerials.add(newSerialSize.get(k).getTitle());
        }

        return finalSerials;

    }

    public ArrayList<String> getNFavorite_asc(ArrayList<Serial> serialList, ArrayList<User> userList, int n, String genre, String year) {
        HashMap<String, Integer> serialFavorites = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null)) &&
                    ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getFavoriteMovies().contains(serialList.get(j).getTitle()) != false)
                        counter += 1;
                }
                if (counter != 0)
                    serialFavorites.put(serialList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newSerialViews.removeIf((mov) -> {
            return (!serialFavorites.containsKey(mov.getTitle()));
        });

        newSerialViews.sort(sortViewSerials(serialFavorites));
        ArrayList<String> finalSerials = new ArrayList<>();
        for (int k = 0; k < n && k < newSerialViews.size(); k++) {

            finalSerials.add(newSerialViews.get(k).getTitle());

        }

        return finalSerials;
    }

    public ArrayList<String> getNFavorite_desc(ArrayList<Serial> serialList, ArrayList<User> userList, int n, String genre, String year) {
        HashMap<String, Integer> serialFavorites = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null)) &&
                    ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getFavoriteMovies().contains(serialList.get(j).getTitle()) != false)
                        counter += 1;
                }
                if (counter != 0)
                    serialFavorites.put(serialList.get(j).getTitle(), counter);
                counter = 0;
            }
        }
        newSerialViews.removeIf((mov) -> {
            return (!serialFavorites.containsKey(mov.getTitle()));
        });

        newSerialViews.sort(sortViewSerials(serialFavorites));
        Collections.reverse(newSerialViews);
        ArrayList<String> finalSerials = new ArrayList<>();
        for (int k = 0; k < n && k < newSerialViews.size(); k++) {

            finalSerials.add(newSerialViews.get(k).getTitle());

        }

        return finalSerials;
    }


    public ArrayList<String> getBestRating_desc(ArrayList<Serial> serialList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> serialRating = new HashMap<>();
        ArrayList<Serial> newSerialRating = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int rating = 0;
            int rating_aux = 0;
            int an = 0;
            int count_apparitions = 0;
            int count_apparitions_aux = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null)) &&
                    ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null && userList.get(k).getRating().get(serialList.get(j).getNumberOfSeasons()) != null) {
                        for (int l = 0; l < userList.get(k).getRating().get(serialList.get(j).getNumberOfSeasons()); l++) {
                            rating_aux += userList.get(k).getRating().get(serialList.get(j).getSeasons().get(k));
                        }
                        rating_aux = (int) (rating_aux / userList.get(k).getRating().get(serialList.get(j).getNumberOfSeasons()));
                    }
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null)
                        rating += userList.get(k).getRating().get(serialList.get(j).getTitle());

                    count_apparitions++;
                }
                if (rating != 0) {
                    rating = rating / count_apparitions;
                    serialRating.put(serialList.get(j).getTitle(), rating);
                }
                rating = 0;
            }
        }
        newSerialRating.removeIf((mov) -> {
            return (!serialRating.containsKey(mov.getTitle()));
        });

        newSerialRating.sort(sortViewSerials(serialRating));
        Collections.reverse(newSerialRating);
        ArrayList<String> finalSerials = new ArrayList<>();
        for (int k = 0; k < n && k < newSerialRating.size(); k++) {

            finalSerials.add(newSerialRating.get(k).getTitle());

        }

        return finalSerials;
    }

    public ArrayList<String> getBestRating_asc(ArrayList<Serial> serialList, ArrayList<User> userList, int n, String genre, String year) {

        HashMap<String, Integer> serialRating = new HashMap<>();
        ArrayList<Serial> newSerialRating = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int rating = 0;
            int rating_aux = 0;
            int an = 0;
            int count_apparitions = 0;
            int count_apparitions_aux = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null)) &&
                    ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null && userList.get(k).getRating().get(serialList.get(j).getNumberOfSeasons()) != null) {
                        for (int l = 0; l < userList.get(k).getRating().get(serialList.get(j).getNumberOfSeasons()); l++) {
                            rating_aux += userList.get(k).getRating().get(serialList.get(j).getSeasons().get(k));
                        }
                        rating_aux = (int) (rating_aux / userList.get(k).getRating().get(serialList.get(j).getNumberOfSeasons()));
                    }
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null)
                        rating += userList.get(k).getRating().get(serialList.get(j).getTitle());

                    count_apparitions++;
                }
                if (rating != 0) {
                    rating = rating / count_apparitions;
                    serialRating.put(serialList.get(j).getTitle(), rating);
                }
                rating = 0;
            }
        }
        newSerialRating.removeIf((mov) -> {
            return (!serialRating.containsKey(mov.getTitle()));
        });

        newSerialRating.sort(sortViewSerials(serialRating));
        ArrayList<String> finalSerials = new ArrayList<>();
        for (int k = 0; k < n && k < newSerialRating.size(); k++) {

            finalSerials.add(newSerialRating.get(k).getTitle());

        }

        return finalSerials;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public static Comparator<Serial> sortViewSerials(HashMap<String, Integer> serialSize) {
        Comparator comp = (Comparator<Serial>) (movi, movf) -> {
            int criteriu = serialSize.get(movi.getTitle()) - serialSize.get(movf.getTitle());
            if (criteriu == 0) {
                return movi.getTitle().compareTo(movf.getTitle());
            }
            return criteriu;
        };
        return comp;
    }
}
