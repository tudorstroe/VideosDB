package classes;

import java.util.ArrayList;

public class Show {
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;

    public Show(final String title, final int year, final ArrayList<String> cast,
                final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
    }

    /**
     *
     * @return it returns Title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return it returns Year
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @return it returns Cast
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     *
     * @return it returns Genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

}
