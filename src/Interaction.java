import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Interaction {
    protected Pattern regexPattern;
    protected Matcher regexMatcher;
    Player player;
    protected Item caller;

    public Interaction(Player player, Item caller){
        this.player = player;
        this.caller = caller;
    }
    private boolean setRegex(String regex, String text){
        regexPattern = Pattern.compile(regex);
        regexMatcher = regexPattern.matcher(text);
        return regexMatcher.find();
    }
    private void exit(){
        UserInterface.inInteraction = false;
    }
    abstract void loop();

}
