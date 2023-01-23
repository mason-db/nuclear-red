public class GIBriccBroken extends Item implements SoloUsable{
    public GIBriccBroken(){
        super("BRICC", "Basic Radio-Interlinked Communication Console. It refuses to turn on, and there is a conspicuously empty slot on its back.", "You pick up the BRICC.", true);
    }
    public void use(){
        UserInterface.outputLine("You press every button, but the BRICC refuses to turn on.");
    }
}
