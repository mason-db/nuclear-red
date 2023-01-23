import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static boolean inInteraction = false;
    public static Player player = new Player();
    public static Scanner in = new Scanner(System.in);
    public static GUI gui;

    public static void main(String[] args) throws Exception{
        gui = new GUI(player);
        Lib.debugPrint("PROGRAM START");
        Lib.initalizeRooms();
        Item.setPlayer(player);
        //todo
        player.setRoom(Lib.getRoom("Cryopod"));
        outputLine(player.getCurrentRoom().getIntroText());
    }
    public static void outputLine(String str){
        String[] lines = str.split("\\r?\\n");
        for(String s : lines) {
            if (s.length() < 80) {
                gui.outputLine(s);
            }
            else {
                String[] words = s.split(" ");
                String currentString = "";
                for (int i = 0; i < words.length; i++) {

                    if (currentString.length() + words[i].length() + 1 <= 80) {
                        currentString += words[i] + " ";
                    } else {
                        gui.outputLine(currentString);
                        currentString = words[i] + " ";
                    }
                }
                gui.outputLine(currentString);
            }
        }
    }
    public static void turn(String text){
        if(!inInteraction){
            //Lib.debugPrint("Attempting to parse \"" + text + "\"");
            TextParser.ReturnCase returnCase = TextParser.parse(text, player);
            if(returnCase == TextParser.ReturnCase.EXPOSURE_TURN){
                int lastExposure = player.getExposure();
                player.increaseExposure(player.getCurrentRoom().radiation);
                if(player.getExposure() / 500 > lastExposure / 500){
                    outputLine("WARNING: Radiation exposure has passed " + ((player.getExposure() / 500) * 500) +
                            " mSv!");
                }
            }
            if(player.getExposure() >= Lib.MAX_EXPOSURE){
                outputLine("WARNING: Maximum exposure reached, shutting down...");
                System.exit(1);
            }
        }
    }

}
