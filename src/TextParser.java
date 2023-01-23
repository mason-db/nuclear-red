import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    public enum ReturnCase{
        INVALID, FREE_TURN, EXPOSURE_TURN   //Invalid if player command, exposure_turn if exposes player to radiation
    }
    private static Pattern regexPattern;
    private static Matcher regexMatcher;

    private static boolean setRegex(String regex, String text){
        regexPattern = Pattern.compile(regex);
        regexMatcher = regexPattern.matcher(text);
        return regexMatcher.find();
    }
    public static ReturnCase parse(String inputText, Player player){
        String input = inputText.toLowerCase();
        if(setRegex("^use .* on ", input)){
            Lib.debugPrint("use x on y");
            setRegex("^use (.*) on (.*)", input);
            String item1String = regexMatcher.group(1);
            String item2String = regexMatcher.group(2);
            if(player.getItems().hasItem(item1String)){
                Item item1 = player.getItems().getItem(item1String);
                Item item2 = player.getItems().getItem(item2String);
                if(item2 == null){
                    item2 = player.getCurrentRoom().items.getItem(item2String);
                    if(item2 == null){
                        UserInterface.outputLine("You don't have that item.");
                        return ReturnCase.EXPOSURE_TURN;
                    }
                }
                if(item1 instanceof ComboUsable){
                    ((ComboUsable) item1).useOn(item2);
                }
                else{
                    UserInterface.outputLine("You can't use " + item1.toString() + "on " + item2.toString() + ".");
                }
            }
            else{
                UserInterface.outputLine("You don't have that item.");
            }
            return ReturnCase.EXPOSURE_TURN;
        }
        else if(setRegex("^use .*", input)){
            Lib.debugPrint("use x");
            setRegex("^use (.*)", input);
            String itemString = regexMatcher.group(1);
            Lib.debugPrint("attempting to use \"" + itemString + "\"");
            if(player.getItems().hasItem(itemString)){
                Item item = player.getItems().getItem(itemString);
                if(item instanceof SoloUsable){
                    ((SoloUsable) item).use();
                }
                else{
                    UserInterface.outputLine("You can't use that!");
                }
            }
            else if(player.getCurrentRoom().items.hasItem(itemString) && !player.getCurrentRoom().items.getItem(itemString).isPickupable()){
                Item item = player.getCurrentRoom().items.getItem(itemString);
                if(item instanceof SoloUsable){
                    ((SoloUsable) item).use();
                }
                else{
                    UserInterface.outputLine("You can't use that!");
                }
            }
            else{
                UserInterface.outputLine("You don't have that item.");
            }
            return ReturnCase.EXPOSURE_TURN;
        }
        else if(setRegex("read .*", input)){
            Lib.debugPrint("read x");
            setRegex("^read (.*)", input);
            String textString = regexMatcher.group(1);
            if(player.getTexts().hasText(textString)){
                UserInterface.outputLine(player.getTexts().getText(textString).getText());
            }
            else{
                UserInterface.outputLine("You don't have that text snippet.");
            }
            return ReturnCase.FREE_TURN;
        }
        else if(input.equals("inspect room") || input.equals("look around")){
            Lib.debugPrint("look around");
            UserInterface.outputLine(player.getCurrentRoom().getIntroText());
            return ReturnCase.FREE_TURN;
        }
        else if(setRegex("^inspect .x", input)){
            Lib.debugPrint("inspect x");
            setRegex("^inspect (.*)", input);
            String thingString = regexMatcher.group(1);
            //room item
            if(player.getCurrentRoom().items.hasItem(thingString)){
                UserInterface.outputLine(player.getCurrentRoom().items.getItem(thingString).flavorText);
                return ReturnCase.EXPOSURE_TURN;
            }
            //room text
            else if(player.getCurrentRoom().texts.hasText(thingString)){
                Text t = player.getCurrentRoom().texts.getText(thingString);
                UserInterface.outputLine(t.getDescription());
                player.getTexts().addText(t);
                return ReturnCase.EXPOSURE_TURN;
            }
            //player item
            else if(player.getItems().hasItem(thingString)){
                UserInterface.outputLine(player.getItems().getItem(thingString).flavorText);
                return ReturnCase.EXPOSURE_TURN;
            }
            //player text
            else if(player.getTexts().hasText(thingString)){
                UserInterface.outputLine(player.getTexts().getText(thingString).getDescription());
                return ReturnCase.FREE_TURN;
            }
            else{
                UserInterface.outputLine("Command not recognized.");
                return ReturnCase.INVALID;
            }
        }
        else if(setRegex("^list (item.*|pocket)", input)){
            Lib.debugPrint("list items");
            UserInterface.outputLine(player.getItems().toString());
            return ReturnCase.FREE_TURN;
        }
        else if(setRegex("^list (text.*|library)", input)){
            Lib.debugPrint("list texts");
            UserInterface.outputLine(player.getTexts().toString());
            return ReturnCase.FREE_TURN;
        }
        else if(setRegex("^pick up|pocket|get|pickup", input)){
            Lib.debugPrint("get x");
            setRegex("^(pick up|pocket|get|pickup) (.*)", input);
            String thingString = regexMatcher.group(2);
            Lib.debugPrint("Attempting to get \"" + thingString + "\"");
            if(player.getCurrentRoom().items.hasItem(thingString)){
                if(player.getCurrentRoom().items.getItem(thingString).isPickupable()){
                    Item item = player.getCurrentRoom().items.removeItem(thingString);
                    player.getItems().addItem(item);
                    UserInterface.outputLine(item.pickupText);
                }
                else{
                    UserInterface.outputLine("You can't pick that up!");
                }
                return ReturnCase.EXPOSURE_TURN;
            }
            else if(player.getCurrentRoom().texts.hasText(thingString)){
                Text text = player.getCurrentRoom().texts.removeText(thingString);
                player.getTexts().addText(text);
                UserInterface.outputLine(text.pickupText);
                return ReturnCase.EXPOSURE_TURN;
            }
            else{
                Lib.debugPrint("Can't find thing to pick up!");
                UserInterface.outputLine("Command not recognized");
                return ReturnCase.INVALID;
            }
        }
        else if(input.equals("leave") || input.equals("leave room")){
            Lib.debugPrint("leave room");
            player.getCurrentRoom().exit(player);
            return ReturnCase.EXPOSURE_TURN;
        }
        else if(setRegex("^enter .*", input)){
            Lib.debugPrint("enter x");
            setRegex("^enter (.*)", input);
            String roomString = regexMatcher.group(1);
            Room.moveRooms(player, roomString);
            return ReturnCase.EXPOSURE_TURN;
        }
        else if(setRegex("^(iodi.e|rad)", input)){
            Lib.debugPrint("iodine");
            UserInterface.outputLine("Current Exposure is " + player.getExposure() + "mSv.");
            return ReturnCase.FREE_TURN;
        }
        else if(setRegex("^help|^\\?", input)){
            Lib.debugPrint("help");
            UserInterface.outputLine("use [item]\nuse [item1] on [item2]\ninspect [thing]\ninspect room\n\t" +
                    "or look around\nlist items\nlist texts\nread [text]\npick up [thing]\n\tor get [thing]\n" +
                    "leave room\nenter [room]\nrad\n\tor iodine\nhelp\n\tor ?\nexit\n\tor quit");
            return ReturnCase.FREE_TURN;
        }
        else if(input.equals("exit") || input.equals("quit") || input.equals("quit game")){
            Lib.debugPrint("quit");
            UserInterface.outputLine("Exiting game...");
            System.exit(2);
            return ReturnCase.INVALID;
        }
        Lib.debugPrint("err");
        UserInterface.outputLine("Command not recognized");
        return ReturnCase.INVALID;
    }

}
