

public class Player {
    private String name;
    private char marker;

    private int score;

    public Player(String name, char marker){
        this.name = name;
        this.marker = marker;
        this.score = 0;
        System.out.printf("Welcome %s. Your assigned marker is: %c%n", getName(), getMarker());
    }

    public String getName(){return this.name;}

    public char getMarker(){return this.marker;}

    public int getScore(){return this.score;}

    public void incrementScore(){
        score+=1;
    }
}
