public class GIPanel extends Item implements SoloUsable {
    public GIPanel(){
        super("Panel", "A control panel of some sort.", null, false);
    }
    public void use(){
        UserInterface.outputLine("TODO: Attempt to use panel here");
    }
}
