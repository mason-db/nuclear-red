public class GIBricc extends Item implements SoloUsable {
    public GIBricc(){
        super("BRICC", "Basic Radio-Interlinked Communication Console", null, true);
    }
    public void use(){
        UserInterface.outputLine("Your BRICC is equipped with many features. In order to view saved texts, type LIST TEXTS.\n" +
                "In order to view current radiation exposure through the IODINE system, type RAD.\n");
    }
}
