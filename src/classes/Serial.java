package classes;

import entertainment.Season;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public final class Serial extends Show {
    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;

    public Serial(final String title, final int year, final ArrayList<String> cast,
                  final ArrayList<String> genres, final int numberOfSeasons,
                  final ArrayList<Season> seasons) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    /**
     * preia cel mai vizualizat serial in mod descrescator
     * @param serialList
     * @param userList
     * @param n
     * @param genre
     * @param year
     * @return
     */
    public ArrayList<String> getMostViewedSerialDesc(final ArrayList<Serial> serialList,
                                                             final ArrayList<User> userList,
                                                             final int n, final String genre,
                                                             final String year) {

        HashMap<String, Integer> serialViews = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<Serial>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (serialList.get(j).getYear() == an
                    && serialList.get(j).getGenres().contains(genre)) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getHistory().get(serialList.get(j).getTitle()) != null) {
                        counter += userList.get(k).getHistory().get(serialList.get(j).getTitle());
                    }
                }
                if (counter != 0) {
                    serialViews.put(serialList.get(j).getTitle(), counter);
                }
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

    /**
     * preia cel mai vizualizat serial in mod crescator
     * @param serialList
     * @param userList
     * @param n
     * @param genre
     * @param year
     * @return
     */
    public ArrayList<String> getMostViewedSerialAsc(final ArrayList<Serial> serialList,
                                                            final ArrayList<User> userList,
                                                            final int n, final String genre,
                                                            final String year) {

        HashMap<String, Integer> serialViews = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<Serial>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (serialList.get(j).getYear() == an
                    && serialList.get(j).getGenres().contains(genre)) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getHistory().get(serialList.get(j).getTitle()) != null) {
                        counter += userList.get(k).getHistory().get(serialList.get(j).getTitle());
                    }
                }
                if (counter != 0) {
                    serialViews.put(serialList.get(j).getTitle(), counter);
                }
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

    /**
     * preia N cele mai lungi filme in mod descrescator, dupa filtre
     * @param serialList
     * @param n
     * @param genre
     * @param year
     * @return
     */
    public ArrayList<String> getNLongestDesc(final ArrayList<Serial> serialList,
                                                    final int n, final String genre,
                                                    final String year) {
        HashMap<String, Integer> serialSize = new HashMap<>();
        ArrayList<Serial> newSerialSize = new ArrayList<>(serialList);
        int an = 0;
        int sizeAux = 0;
        for (int j = 0; j < serialList.size(); j++) {

            if (year != null) {

                an = Integer.parseInt(year);

            }

            if (((serialList.get(j).getYear() == an) || (year == null))
                    && ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {
                sizeAux = 0;
                for (int k = 0; k < serialList.get(j).numberOfSeasons; k++) {

                    sizeAux += serialList.get(j).getSeasons().get(k).getDuration();

                }

                serialSize.put(serialList.get(j).getTitle(), sizeAux);


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

    /**
     * preia N cele mai lungi filme in mod crescator, dupa filtre
     * @param serialList
     * @param n
     * @param genre
     * @param year
     * @return
     */
    public ArrayList<String> getNLongestAsc(final ArrayList<Serial> serialList,
                                                   final int n, final String genre,
                                                   final String year) {
        HashMap<String, Integer> serialSize = new HashMap<>();
        ArrayList<Serial> newSerialSize = new ArrayList<>(serialList);
        int counter = 0;
        int an = 0;
        int sizeAux = 0;
        for (int j = 0; j < serialList.size(); j++) {

            if (year != null) {

                an = Integer.parseInt(year);

            }

            if (((serialList.get(j).getYear() == an) || (year == null))
                    && ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {
                sizeAux = 0;
                for (int k = 0; k < serialList.get(j).numberOfSeasons; k++) {

                    sizeAux += serialList.get(j).getSeasons().get(k).getDuration();

                }

                serialSize.put(serialList.get(j).getTitle(), sizeAux);


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

    /**
     * preia N cele mai adaugate la favorite filme in mod crescator, dupa filtre
     * @param serialList
     * @param userList
     * @param n
     * @param genre
     * @param year
     * @return
     */
    public ArrayList<String> getNFavoriteAsc(final ArrayList<Serial> serialList,
                                                    final ArrayList<User> userList, final int n,
                                                    final String genre, final String year) {
        HashMap<String, Integer> serialFavorites = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null))
                    && ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getFavoriteMovies().contains(serialList.get(j).
                            getTitle())) {
                        counter += 1;
                    }
                }
                if (counter != 0) {
                    serialFavorites.put(serialList.get(j).getTitle(), counter);
                }
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

    /**
     * preia N cele mai adaugate la favorite filme in mod descrescator, dupa filtre
     * @param serialList
     * @param userList
     * @param n
     * @param genre
     * @param year
     * @return
     */

    public ArrayList<String> getNFavoriteDesc(final ArrayList<Serial> serialList,
                                                     final ArrayList<User> userList, final int n,
                                                     final String genre, final String year) {
        HashMap<String, Integer> serialFavorites = new HashMap<>();
        ArrayList<Serial> newSerialViews = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int counter = 0;
            int an = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null))
                    && ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getFavoriteMovies().contains(serialList.get(j).
                            getTitle())) {
                        counter += 1;
                    }
                }
                if (counter != 0) {
                    serialFavorites.put(serialList.get(j).getTitle(), counter);
                }
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

    /**
     * prena N cele mai bine rateuite filme, in mod descrescator, dupa filtre
     * @param serialList
     * @param userList
     * @param n
     * @param genre
     * @param year
     * @return
     */
    public ArrayList<String> getBestRatingDesc(final ArrayList<Serial> serialList,
                                                      final ArrayList<User> userList, final int n,
                                                      final String genre, final String year) {

        HashMap<String, Integer> serialRating = new HashMap<>();
        ArrayList<Serial> newSerialRating = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int rating = 0;
            int ratingAux = 0;
            int an = 0;
            int countApparitions = 0;
            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null))
                    && ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null
                            && userList.get(k).getRating().get(serialList.get(j).
                            getNumberOfSeasons()) != null) {
                        for (int l = 0; l < userList.get(k).getRating().get(serialList.get(j).
                                getNumberOfSeasons()); l++) {
                            ratingAux += userList.get(k).getRating().get(serialList.get(j).
                                    getSeasons().get(k));
                        }
                        ratingAux = (int) (ratingAux / userList.get(k).getRating().
                                get(serialList.get(j).getNumberOfSeasons()));
                    }
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null) {
                        rating += userList.get(k).getRating().get(serialList.get(j).getTitle());
                    }

                    countApparitions++;
                }
                if (rating != 0) {
                    rating = rating / countApparitions;
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

    /**
     * prena N cele mai bine rateuite filme, in mod crescator, dupa filtre
     * @param serialList
     * @param userList
     * @param n
     * @param genre
     * @param year
     * @return
     */
    public ArrayList<String> getBestRatingAsc(final ArrayList<Serial> serialList,
                                                     final ArrayList<User> userList, final int n,
                                                     final String genre, final String year) {

        HashMap<String, Integer> serialRating = new HashMap<>();
        ArrayList<Serial> newSerialRating = new ArrayList<>(serialList);
        for (int j = 0; j < serialList.size(); j++) {
            int rating = 0;
            int ratingAux = 0;
            int an = 0;
            int countApparitions = 0;

            if (year != null) {

                an = Integer.parseInt(year);

            }
            if (((serialList.get(j).getYear() == an) || (year == null))
                    && ((serialList.get(j).getGenres().contains(genre)) || (genre == null))) {

                for (int k = 0; k < userList.size(); k++) {
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null
                            && userList.get(k).getRating().get(serialList.get(j).
                            getNumberOfSeasons()) != null) {
                        for (int l = 0; l < userList.get(k).getRating().get(serialList.
                                get(j).getNumberOfSeasons()); l++) {
                            ratingAux += userList.get(k).getRating().get(serialList.get(j).
                                    getSeasons().get(k));
                        }
                        ratingAux = (int) (ratingAux / userList.get(k).getRating().get(serialList.
                                get(j).getNumberOfSeasons()));
                    }
                    if (userList.get(k).getRating().get(serialList.get(j).getTitle()) != null) {
                        rating += userList.get(k).getRating().get(serialList.get(j).getTitle());
                    }

                    countApparitions++;
                }
                if (rating != 0) {
                    rating = rating / countApparitions;
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

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * sorteaza serialele
     * @param serialSize
     * @return
     */
    public Comparator<Serial> sortViewSerials(final // using lambda operator
                                                           HashMap<String, Integer> serialSize) {
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
