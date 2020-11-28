package main;
// single_query_most_viewed_movie.json
import checker.Checkstyle;
import checker.Checker;
import classes.Actor;
import classes.Movie;
import classes.Serial;
import classes.User;
import common.Constants;
import fileio.*;
import org.json.simple.JSONArray;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {}

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file: Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        //TODO add here the entry point to your implementation
        List < String > spacewordz = new ArrayList < String > ();
        HashMap < String, Double> ratings = new HashMap <String, Double> ();
        int stop = 0;

        String result = new String();
        ArrayList < User > userList = new ArrayList < > ();
        for (UserInputData userInputData: input.getUsers()) {
            User user = new User(userInputData.getUsername(), userInputData.getSubscriptionType(), userInputData.getHistory(), userInputData.getFavoriteMovies(), ratings);
            userList.add(user);
        }

        ArrayList < Actor > actorList = new ArrayList < > ();
        for (ActorInputData actorInputData: input.getActors()) {
            Actor actor = new Actor(actorInputData.getName(), actorInputData.getCareerDescription(), actorInputData.getFilmography(), actorInputData.getAwards());
            actorList.add(actor);
        }

        ArrayList < Movie > movieList = new ArrayList < > ();
        for (MovieInputData movieInputData: input.getMovies()) {
            Movie movie = new Movie(movieInputData.getTitle(), movieInputData.getYear(), movieInputData.getCast(), movieInputData.getGenres(), movieInputData.getDuration());
            movieList.add(movie);
        }

        ArrayList <Serial> serialList =  new ArrayList<>();
        for (SerialInputData serialInputData: input.getSerials()) {
            Serial serial = new Serial(serialInputData.getTitle(), serialInputData.getYear(), serialInputData.getCast(), serialInputData.getGenres(), serialInputData.getNumberSeason(), serialInputData.getSeasons());
            serialList.add(serial);
        }

        HashMap < String, Integer > movieViews = new HashMap < > ();

        for (int i = 0; i < input.getCommands().size(); i++) {
            if (input.getCommands().get(i).getActionType().equals(Constants.COMMAND)) {

                if (input.getCommands().get(i).getType().equals(Constants.FAVORITE)) {
                    for (int j = 0; j < userList.size(); j++)
                        if (input.getCommands().get(i).getUsername().equals(userList.get(j).getUsername()))
                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null,
                                    userList.get(j).addFavorite(input.getCommands().get(i).getTitle())));
                }

                if (input.getCommands().get(i).getType().equals(Constants.VIEW)) {
                    for (int j = 0; j < userList.size(); j++)
                        if (input.getCommands().get(i).getUsername().equals(userList.get(j).getUsername()))
                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null,
                                    userList.get(j).addViewed(input.getCommands().get(i).getTitle())));

                }

                if (input.getCommands().get(i).getType().equals(Constants.RATING)) {
                    for (int j = 0; j < userList.size(); j++)
                        if (input.getCommands().get(i).getUsername().equals(userList.get(j).getUsername()))
                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null,
                                    userList.get(j).addRating(input.getCommands().get(i).getTitle(), input.getCommands().get(i).getGrade())));

                }
            }

            if (input.getCommands().get(i).getActionType().equals(Constants.QUERY)) {

                if (input.getCommands().get(i).getObjectType().equals(Constants.ACTORS)) {

                    if (input.getCommands().get(i).getCriteria().equals(Constants.FILTER_DESCRIPTIONS)) {

                        if (stop == 0) {
                            List < String > wordz = input.getCommands().get(i).getFilters().get(2);
                            String auxiliar = new String();
                            for (int k = 0; k < wordz.size(); k++) {
                                auxiliar = wordz.get(k);
                                auxiliar = " " + auxiliar;
                                spacewordz.add(auxiliar);
                            }
                            stop = 1;
                        }
                        if (input.getCommands().get(i).getSortType().equals(Constants.ASC)) {

                            Collections.sort(actorList);

                            System.out.println(input.getCommands().get(i).getActionId());
                            for (int j = 0; j < actorList.size(); j++) {
                                System.out.println(actorList.get(j).getName());
                                result = result + actorList.get(j).getFilterDescription(actorList.get(j).getCareerDescription(), spacewordz);
                            }
                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: [" + result + "]"));
                        }

                        if (input.getCommands().get(i).getSortType().equals(Constants.DESC)) {

                            Collections.sort(actorList, Collections.reverseOrder());
                            System.out.println(input.getCommands().get(i).getActionId());
                            for (int j = 0; j < actorList.size(); j++) {
                                System.out.println(actorList.get(j).getName());
                                result = result + actorList.get(j).getFilterDescription(actorList.get(j).getCareerDescription(), spacewordz);
                            }
                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: [" + result + "]"));
                        }

                    }
                }
                if (input.getCommands().get(i).getObjectType().equals(Constants.MOVIES)) {



                    if (input.getCommands().get(i).getCriteria().equals((Constants.MOST_VIEWED))) {

                        ArrayList < Movie > newMovieViews = new ArrayList < > (movieList);

                        if (input.getCommands().get(i).getSortType().equals(Constants.DESC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getMostViewed_desc(movieList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));
                        }

                        if (input.getCommands().get(i).getSortType().equals(Constants.ASC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getMostViewed_asc(movieList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));
                        }


                    }

                    if (input.getCommands().get(i).getCriteria().equals((Constants.LONGEST))) {

                        if(input.getCommands().get(i).getSortType().equals(Constants.ASC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getNLongest_asc(movieList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));

                        }

                        if(input.getCommands().get(i).getSortType().equals(Constants.DESC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getNLongest_desc(movieList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));


                        }

                    }

                    if (input.getCommands().get(i).getCriteria().equals(Constants.RATINGS)) {

                        if(input.getCommands().get(i).getSortType().equals(Constants.DESC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getBestRating_desc(movieList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));
                        }

                        if(input.getCommands().get(i).getSortType().equals(Constants.ASC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getBestRating_asc(movieList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));
                        }

                    }

                    if (input.getCommands().get(i).getCriteria().equals(Constants.FAVORITE)) {

                        if(input.getCommands().get(i).getSortType().equals(Constants.ASC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getNFavorite_asc(movieList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString()));
                        }

                        if(input.getCommands().get(i).getSortType().equals(Constants.DESC)) {

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + movieList.get(0).getNFavorite_desc(movieList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString()));
                        }
                    }
                }
                if (input.getCommands().get(i).getObjectType().equals(Constants.SHOWS)) {

                    if (input.getCommands().get(i).getCriteria().equals((Constants.MOST_VIEWED))) {

                        if (input.getCommands().get(i).getSortType().equals(Constants.DESC)) {

                            if(serialList.size()!=0)

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + serialList.get(0).getMostViewed_serial_desc(serialList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));

                            else

                            arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: []" )); // daca seriallist nu are elemente, rezultatul este null

                        }

                        if (input.getCommands().get(i).getSortType().equals(Constants.ASC)) {

                            if(serialList.size()!=0)

                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + serialList.get(0).getMostViewed_serial_asc(serialList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString() ));

                            else

                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: []" )); // daca seriallist nu are elemente, rezultatul este null

                        }
                    }

                    if(input.getCommands().get(i).getCriteria().equals(Constants.LONGEST)) {
                        if (input.getCommands().get(i).getSortType().equals(Constants.DESC)) {

                            if (serialList.size() != 0)
                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + serialList.get(0).getNLongest_desc(serialList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString()));


                            else

                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: []")); // daca seriallist nu are elemente, rezultatul este null
                        }

                        if (input.getCommands().get(i).getSortType().equals(Constants.ASC)) {

                            if (serialList.size() != 0)
                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + serialList.get(0).getNLongest_asc(serialList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString()));


                            else

                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: []")); // daca seriallist nu are elemente, rezultatul este null
                    }

                    }

                    if(input.getCommands().get(i).getCriteria().equals(Constants.FAVORITE)) {
                        if (input.getCommands().get(i).getSortType().equals(Constants.ASC)) {
                            if (serialList.size() != 0)
                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + serialList.get(0).getNFavorite_asc(serialList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString()));
                            else

                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: []")); // daca seriallist nu are elemente, rezultatul este null
                        }

                        if (input.getCommands().get(i).getSortType().equals(Constants.DESC)) {
                            if (serialList.size() != 0)
                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: " + serialList.get(0).getNFavorite_desc(serialList, userList, input.getCommands().get(i).getNumber(), input.getCommands().get(i).getFilters().get(1).get(0), input.getCommands().get(i).getFilters().get(0).get(0)).toString()));
                            else

                                arrayResult.add(fileWriter.writeFile(input.getCommands().get(i).getActionId(), null, "Query result: []")); // daca seriallist nu are elemente, rezultatul este null
                        }


                    }

                }
            }
        }

        fileWriter.closeJSON(arrayResult);
    }


}