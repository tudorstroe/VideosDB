package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Movie extends Show {
    private int duration;

    public Movie(String title, int year, ArrayList<String> cast, ArrayList<String> genres,  int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
