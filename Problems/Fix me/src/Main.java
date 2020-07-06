import java.util.*;     

/** Observable */

/** Concrete Observable */
class RockstarGames {

    public String releaseGame;
   // Gamer gamer = new Gamer();

    public void release(String game) {
        this.releaseGame = game;
        /** write your code here ... */
    }
    public void notifyObservers(Gamer gamer) {
        //for (Observer observer : observers) {
            System.out.println("Inform message to : " + gamer.name);
          //  observer.update(releaseGame);
       // }
    }

}

/** Observer */

/** Concrete Observer */
class Gamer {

    public String name;
    public String reaction;
    public RockstarGames rockstarGames;

    private Set<String> games = new HashSet<>();

    public Gamer(String name,String reaction,RockstarGames rockstarGames) {
        this.reaction = reaction;
        this.rockstarGames=rockstarGames;
        this.name = name;
    }

    /** write your code here ... */

    public void buyGame(String game) {
       // System.out.println(name + " says : " + reaction + game + " !\"");
        games.add(game);
        System.out.println(reaction);
    }

    @Override
    public String toString() {
        return this.name;
    }    
}

/** Main Class */

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        String game = null;
        RockstarGames rockstarGames = new RockstarGames();

        Gamer garry = new Gamer("Garry Rose", "I want to pre-order", rockstarGames);
        Gamer peter = new Gamer("Peter Johnston", "Pinch me...", rockstarGames);
        Gamer helen = new Gamer("Helen Jack", "Jesus, it's new game from Rockstar!", rockstarGames);

        /** write your code here ... */

        game = scanner.nextLine();
        System.out.println("It's happened! RockstarGames releases new game - " + game + "!");

        rockstarGames.notifyObservers(garry);
        System.out.println(garry.name + " say: " + garry.reaction);
        rockstarGames.notifyObservers(peter);
        System.out.println(peter.name + " say: " + peter.reaction);
        rockstarGames.notifyObservers(helen);
        System.out.println(helen.name + " say: " + helen.reaction);

        scanner.close();
    }
}