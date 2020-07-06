import java.util.*;

/**
 * Observable interface
 **/
interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

    void observersReact();
}

/**
 * Concrete Observable - Rockstar Games
 **/
class RockstarGames implements Observable {

    public String releaseGame;

    private List<Observer> observers = new ArrayList<>();

    public void release(String game) {
        this.releaseGame = game;
        notifyObservers();
        //observersReact();
    }

    public void dontRelease(String game) {
        this.releaseGame = game;
        observersReact();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            System.out.println("Notification for gamer : " + observer);
            observer.update(releaseGame);
        }
    }

    @Override
    public void observersReact() {
        for (Observer observer : observers) {
            System.out.println("Notification for gamer : " + observer);
            observer.updater();
        }
    }
}

/**
 * Observer interface
 **/
interface Observer {
    public void update(String domain);

    public void updater();
}

/**
 * Concrete observer - Gamer
 **/
class Gamer implements Observer {

    private String name;
    private Observable observable;
    private Set<String> games = new HashSet<>();

    public Gamer(String name, Observable observable) {
        this.name = name;
        this.observable = observable;
    }

    @Override
    public void update(String game) {
        buyGame(game);
        // dontBuyGame();
    }

    @Override
    public void updater() {
        dontBuyGame();
    }

    public void buyGame(String game) {
        System.out.println(name + " says : \"Oh, Rockstar releases new game " + game + " !\"");
        games.add(game);
    }

    public void dontBuyGame() {
        System.out.println("What? They've already released this game ... I don't understand");
    }

    @Override
    public String toString() {
        return this.name;
    }
}

/**
 * Main class
 **/
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String game = null;
        String newGame = null;
        RockstarGames rockstarGames = new RockstarGames();

        Gamer garry = new Gamer("Garry Rose", rockstarGames);
        Gamer peter = new Gamer("Peter Johnston", rockstarGames);
        Gamer helen = new Gamer("Helen Jack", rockstarGames);

        rockstarGames.addObserver(garry);
        rockstarGames.addObserver(peter);
        rockstarGames.addObserver(helen);

        for (int i = 0; i < 2; i++) {
            game = scanner.nextLine();
            if (i == 1 && newGame.equals(game)) {
                rockstarGames.dontRelease(game);
            } else {
                newGame = game;
                rockstarGames.release(game);
            }
        }

        scanner.close();
    }
}