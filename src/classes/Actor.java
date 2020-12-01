package classes;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Actor implements Comparable<Actor> {
    private final String name;
    private String careerDescription;
    private final ArrayList<String> filmography;
    private final Map<ActorsAwards, Integer> awards;

    public Actor(final String name, final String careerDescription, final ArrayList<String>
            filmography, final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
    }

    /**
     *
     * @param careerDesc
     * @param words key-urile dupa care se sorteaza
     * @return
     */
    public String getFilterDescription(final String careerDesc,
                                             final List<String> words) {
        ArrayList<String> wordsarray = new ArrayList<String>();

        for (int i = 0; i < words.size(); i++) {
            wordsarray.add(words.get(i));
        }
        boolean matches = true;
        for (String word : wordsarray) {
            if (!careerDesc.toLowerCase().contains(word)) {
                matches = false;
                break;
            }

        }
        if (matches) {
            return this.getName();
        } else {
            return "";
        }
    }

    @Override
    public int compareTo(final Actor o) {
        return this.getName().compareTo(o.getName());
    }

    public String getName() {
        return name;
    }


    public String getCareerDescription() {
        return careerDescription;
    }


    public ArrayList<String> getFilmography() {
        return filmography;
    }


    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

}
