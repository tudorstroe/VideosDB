package classes;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Actor implements Comparable<Actor>{
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;

    public Actor(String name, String careerDescription, ArrayList<String> filmography, Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
    }

    String auxiliar = new String();

    public String getFilterDescription(String CareerDescription, List<String> words) {
        ArrayList<String> wordsarray= new ArrayList<String>();

        for (int i=0; i<words.size(); i++) {
            wordsarray.add(words.get(i));
        }
        CareerDescription = CareerDescription.toLowerCase();
        boolean matches = true;
        for (String word : wordsarray) {
            if (!CareerDescription.contains(word)) {
                matches = false;
                break;
            }

        }
        if (matches==true)
            return this.getName();
        else
            return "";
    }

    @Override
    public int compareTo(Actor o) {
        return this.getName().compareTo(o.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public void setAwards(Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }
}
