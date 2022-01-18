package RaceGame;

import java.util.Scanner;

public class Game {
    Scanner sc = new Scanner(System.in);
    Track track = new Track();
    String [] raceTrack = track.getTrack();

    public void playGame(){
        printText();
        char charForStart = sc.next().charAt(0);
        long startTime = System.currentTimeMillis()/1000;
        for(int i = 0; i < raceTrack.length - 1; i++){
            System.out.print(raceTrack[i]);
            int currentPosition = raceTrack[i].indexOf('V');
            int newPosition = makeMove(currentPosition);
            if(controlOfPosition(raceTrack[i+1], newPosition)){
                StringBuilder sb = new StringBuilder(raceTrack[i+1]);
                sb.setCharAt(newPosition, 'V');
                raceTrack[i+1] = sb.toString();
            } else {
                long endTime = System.currentTimeMillis()/1000;
                System.out.println("You crashed in this game. Better luck next time!");
                System.out.println("Your race-time was " + (endTime - startTime) + " seconds");
                System.exit(0);
            }
        }
        System.out.println(raceTrack[raceTrack.length-1]);
        long endTime = System.currentTimeMillis()/1000;
        System.out.println("You won the whole race!");
        System.out.println("Your race-time was " + (endTime - startTime) + " seconds");
        System.exit(0);
    }

    private boolean controlOfPosition(String raceTrack, int newPosition) {
        if(raceTrack.charAt(newPosition) == '*' || newPosition == raceTrack.indexOf('*')-1 || newPosition == raceTrack.indexOf('*')+7){
            return false;
        } else{
            return true;
        }
    }

    private int makeMove(int currentPosition) {
        int newPosition = 0;
        boolean correct = false;
        int left = -1;
        int straight = 0;
        int right = 1;
        while(!correct){
            char move = sc.next().charAt(0);
            if(move == 'a'){
                newPosition += currentPosition + left;
                correct = true;
            }
            if(move == 's'){
                newPosition += currentPosition + straight;
                correct = true;
            }
            if(move == 'd'){
                newPosition += currentPosition + right;
                correct = true;
            }
        }
        return newPosition;
    }

    private void printText(){
        System.out.println("Welcome to text-racing");
        System.out.println("Press 'a' followed by <enter> to turn left, \n"
        + "'s' followed by <enter> to go straight, \n"
        + "and 'd' followed by <enter> to turn right");
        System.out.println("__________________________________");
        System.out.println("Press any letter followed by <enter> to start the race-timer");
    }
}
