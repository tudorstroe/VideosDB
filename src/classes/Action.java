package classes;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private int actionId;
    private final String actionType;
    private final String type;
    private final String username;
    private final String objectType;
    private final String sortType;
    private final String criteria;
    private final String title;
    private final String genre;
    private final int number;
    private final double grade;
    private final int seasonNumber;
    private List<List<String>> filters = new ArrayList<>();

    public Action(final int actionId, final String actionType, final String type,
                  final String username, final String objectType, final String sortType,
                  final String criteria, final String title, final String genre, final int number,
                  final double grade, final int seasonNumber, final List<List<String>> filters) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.type = type;
        this.username = username;
        this.objectType = objectType;
        this.sortType = sortType;
        this.criteria = criteria;
        this.title = title;
        this.genre = genre;
        this.number = number;
        this.grade = grade;
        this.seasonNumber = seasonNumber;
        this.filters = filters;
    }

    /**
     *
     * @return returneaza actionid
     */
    public int getActionId() {
        return actionId;
    }

    /**
     *
     * @return returneaza ActionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     *
     * @return returneaza Type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return returneaza Username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return returneaza SortType
     */
    public String getSortType() {
        return sortType;
    }

    /**
     *
     * @return returneaza Criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     *
     * @return returneaza Title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return returneaza Genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @return returneaza Number
     */
    public int getNumber() {
        return number;
    }

    /**
     *
     * @return returneaza Grade
     */
    public double getGrade() {
        return grade;
    }
    /**
     *
     * @return returneaza SeasonNumber
     */
    public int getSeasonNumber() {
        return seasonNumber;
    }
    /**
     *
     * @return returneaza Filters
     */
    public List<List<String>> getFilters() {
        return filters;
    }

}
